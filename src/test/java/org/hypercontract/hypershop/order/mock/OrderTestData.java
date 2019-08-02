package org.hypercontract.hypershop.order.mock;

import org.hypercontract.hypershop.order.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OrderTestData {

    private static final int ORDER_COUNT = 3;

    private final List<Order> orders;
    private final Order order;

    private OrderTestData() {
        var orderItemTestData = OrderItemTestData.getInstance();
        var orderAddressTestData = OrderAddressTestData.getInstance();
        var orderPaymentTestData = OrderPaymentTestData.getInstance();

        this.orders = Stream.generate(() -> Order.builder()
                .items(orderItemTestData.getOrderItems())
                .billingAddress(orderAddressTestData.getOrderAddresses().get(0))
                .shippingAddress(orderAddressTestData.getOrderAddresses().get(1))
                .payment(orderPaymentTestData.getOrderPayment())
                .build())
            .limit(ORDER_COUNT)
            .collect(Collectors.toUnmodifiableList());
        
        this.order = this.orders.get(0);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrder() {
        return order;
    }

    private static OrderTestData instance;

    public static OrderTestData getInstance () {
        if (OrderTestData.instance == null) {
            OrderTestData.instance = new OrderTestData();
        }
        return OrderTestData.instance;
    }
}
