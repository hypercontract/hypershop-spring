package org.hypercontract.hypershop.orders.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Order {

    @Getter
    @Builder.Default
    @JsonUnwrapped(prefix = "_")
    private final Id<Order> id = new Id();

    @Getter
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;

    @Getter
    private final LocalDateTime date;

    @Getter
    private final List<OrderItem> items;

    @Getter
    private final OrderAddress billingAddress;

    @Getter
    private final OrderAddress shippingAddress;

    @Getter
    private final OrderPayment payment;

    public static class OrderBuilder {}
}
