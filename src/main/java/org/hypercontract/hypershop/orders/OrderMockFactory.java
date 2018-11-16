package org.hypercontract.hypershop.orders;

import com.github.javafaker.Faker;
import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OrderMockFactory {

    private final Faker faker = new Faker();

    public List<Order> createOrders(List<ShoppingCart> shoppingCarts, List<Address> addresses, List<PaymentOption> paymentOptions) {
        return shoppingCarts.stream()
                .map(shoppingCart -> createOrder(shoppingCart, addresses, paymentOptions))
                .collect(Collectors.toList());
    }

    private Order createOrder(ShoppingCart shoppingCart, List<Address> addresses, List<PaymentOption> paymentOptions) {
        return Order.builder()
                .fromShoppingCart(shoppingCart)
                .date(getRandomDate())
                .status(getRandomOrderStatus())
                .billingAddress(getRandomAddress(addresses))
                .shippingAddress(getRandomAddress(addresses))
                .payment(getRandomPaymentOption(paymentOptions))
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
