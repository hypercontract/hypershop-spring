package org.hypercontract.hypershop.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Order {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
    private final Id<Order> id = new Id();

    @Getter
    @Setter
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
