package org.hypercontract.hypershop.order.mock;

import org.hypercontract.hypershop.order.OrderItem;
import org.hypercontract.hypershop.shoppingCart.mock.ShoppingCartItemTestData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemTestData {

    private static final int ORDER_ITEM_COUNT = 3;

    private final List<OrderItem> orderItems;
    private final OrderItem orderItem;

    private OrderItemTestData() {
        this.orderItems = ShoppingCartItemTestData.getInstance().getShoppingCartItems().stream()
            .limit(ORDER_ITEM_COUNT)
            .map(shoppingCartItem -> OrderItem.builder()
                .name(shoppingCartItem.getName())
                .description(shoppingCartItem.getDescription())
                .product(shoppingCartItem.getProduct())
                .quantity(shoppingCartItem.getQuantity())
                .price(shoppingCartItem.getPrice())
                .build())
            .collect(Collectors.toUnmodifiableList());

        this.orderItem = this.orderItems.get(0);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    private static OrderItemTestData instance;

    public static OrderItemTestData getInstance () {
        if (OrderItemTestData.instance == null) {
            OrderItemTestData.instance = new OrderItemTestData();
        }
        return OrderItemTestData.instance;
    }
}
