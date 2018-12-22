package org.hypercontract.hypershop.orders;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.orders.jpa.OrderEntityMapper;
import org.hypercontract.hypershop.orders.jpa.OrderRepository;
import org.hypercontract.hypershop.orders.model.NewOrder;
import org.hypercontract.hypershop.orders.model.Order;
import org.hypercontract.hypershop.orders.model.OrderStatus;
import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderMockFactory {

    private final Faker faker = new Faker();

    private final OrderRepository orderRepository;

    private final OrderEntityMapper orderEntityMapper = Mappers.getMapper(OrderEntityMapper.class);
    private final OrderItemMapper itemMapper = Mappers.getMapper(OrderItemMapper.class);
    private final OrderAddressMapper addressMapper = Mappers.getMapper(OrderAddressMapper.class);
    private final OrderPaymentMapper paymentMapper = Mappers.getMapper(OrderPaymentMapper.class);

    @Transactional
    public List<Order> createOrders(List<ShoppingCart> shoppingCarts, List<Address> addresses, List<PaymentOption> paymentOptions) {
        return shoppingCarts.stream()
            .map(shoppingCart -> createOrder(shoppingCart, addresses, paymentOptions))
            .peek(order -> orderRepository.save(
                orderEntityMapper.toEntity(order)
            ))
            .collect(Collectors.toList());
    }

    private Order createOrder(ShoppingCart shoppingCart, List<Address> addresses, List<PaymentOption> paymentOptions) {
        return Order.builder()
            .items(itemMapper.toOrderItems(shoppingCart.getItems()))
            .billingAddress(addressMapper.toOrderAddress(getRandomAddress(addresses)))
            .shippingAddress(addressMapper.toOrderAddress(getRandomAddress(addresses)))
            .payment(paymentMapper.toOrderPayment(getRandomPaymentOption(paymentOptions)))
            .date(getRandomDate())
            .status(getRandomOrderStatus())
            .build();
    }

    private NewOrder getRandomNewOrder(List<ShoppingCartItem> shoppingCartItems, Address billingAddress, Address shippingAddress, PaymentOption payment) {
        return NewOrder.builder()
            .items(shoppingCartItems.stream()
                .map(shoppingCartItem -> shoppingCartItem.getId())
                .collect(Collectors.toList())
            )
            .billingAddress(billingAddress.getId())
            .shippingAddress(shippingAddress.getId())
            .payment(payment.getId())
            .build();
    }

    private OrderStatus getRandomOrderStatus() {
        var statusValues = OrderStatus.values();
        Random random = new Random();
        var index = random.nextInt(statusValues.length);
        return statusValues[index];
    }

    private LocalDateTime getRandomDate() {
        return faker.date().past(365 * 5, TimeUnit.DAYS)
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    private Address getRandomAddress(List<Address> address) {
        Random random = new Random();
        var index = random.nextInt(address.size());
        return address.get(index);
    }

    private PaymentOption getRandomPaymentOption(List<PaymentOption> paymentOption) {
        Random random = new Random();
        var index = random.nextInt(paymentOption.size());
        return paymentOption.get(index);
    }
}
