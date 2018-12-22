package org.hypercontract.hypershop.orders;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.mock.MockData;
import org.hypercontract.hypershop.orders.jpa.OrderEntityMapper;
import org.hypercontract.hypershop.orders.jpa.OrderRepository;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.product.jpa.ProductEntityMapper;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
class OrderService {

    private final OrderRepository orderRepository;

    private final OrderEntityMapper entityMapper = Mappers.getMapper(OrderEntityMapper.class);


    public Order getById(Id<Order> id) {
        return orderRepository.findById(id.toString())
            .map(entityMapper::toOrder)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Order> findAll() {
        try(var orders = orderRepository.findAll()) {
            return orders
                .map(entityMapper::toOrder)
                .collect(Collectors.toList());
        }
    }

    public Order create(
        List<ShoppingCartItem> shoppingCartItems,
        Address billingAddress,
        Address shippingAddress,
        PaymentOption payment
    ) {
        Order order = Order.builder()
            .fromShoppingCartItems(shoppingCartItems)
            .fromBillingAddress(billingAddress)
            .fromShippingAddress(shippingAddress)
            .fromPaymentOption(payment)
            .build();

        orderRepository.save(
            entityMapper.toEntity(order)
        );

        return order;
    }

}
