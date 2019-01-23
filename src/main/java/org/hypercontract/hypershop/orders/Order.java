package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
    @javax.persistence.Id
    private Id<Order> id = new Id();

    @Getter
    @Setter
    private OrderStatus status;

    @Getter
    private LocalDateTime date;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @Getter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderAddress billingAddress;

    @Getter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderAddress shippingAddress;

    @Getter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderPayment payment;

    public static class OrderBuilder {}
}
