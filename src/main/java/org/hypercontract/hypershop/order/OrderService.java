package org.hypercontract.hypershop.order;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.factory.Mappers;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hypercontract.hypershop.order.OrderStatus.*;

@Service
@AllArgsConstructor
class OrderService {

    private final ApplicationEventPublisher eventPublisher;

    private final OrderRepository orderRepository;

    private final OrderItemMapper itemMapper = Mappers.getMapper(OrderItemMapper.class);
    private final OrderAddressMapper addressMapper = Mappers.getMapper(OrderAddressMapper.class);
    private final OrderPaymentMapper paymentMapper = Mappers.getMapper(OrderPaymentMapper.class);

    @Transactional(readOnly = true)
    public Order getById(Id<Order> id) {
        return orderRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        try(var orders = orderRepository.findAll()) {
            return orders
                .collect(Collectors.toList());
        }
    }

    @Transactional
    public Order placeOrder(
        List<ShoppingCartItem> shoppingCartItems,
        Address billingAddress,
        Address shippingAddress,
        PaymentOption paymentOption
    ) {
        Order order = Order.builder()
            .status(PAYMENT_DUE)
            .items(itemMapper.toOrderItems(shoppingCartItems))
            .billingAddress(addressMapper.toOrderAddress(billingAddress))
            .shippingAddress(addressMapper.toOrderAddress(shippingAddress))
            .payment(paymentMapper.toOrderPayment(paymentOption))
            .date(LocalDateTime.now())
            .build();

        save(order);

        eventPublisher.publishEvent(new OrderPlaced());

        return order;
    }

    @Transactional
    public Order cancelOrder(Id<Order> id) {
        return updateStatus(id, CANCELLED);
    }

    @Transactional
    public Order returnOrder(Id<Order> id) {
        return updateStatus(id, RETURNED);
    }

    private Order updateStatus(Id<Order> id, OrderStatus status) {
        Order order = getById(id);
        order.setStatus(status);
        return save(order);
    }

    private Order save(Order order) {
        orderRepository.save(order);
        return order;
    }
}
