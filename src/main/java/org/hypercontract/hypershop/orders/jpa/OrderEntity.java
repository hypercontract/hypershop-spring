package org.hypercontract.hypershop.orders.jpa;

import lombok.Data;
import lombok.Getter;
import org.hypercontract.hypershop.orders.OrderItem;
import org.hypercontract.hypershop.orders.OrderStatus;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class OrderEntity {

    @Id
    private String id;

    private OrderStatus status;

    private LocalDateTime date;

    @OneToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "order"
    )
    private List<OrderItemEntity> items;

    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private OrderAddressEntity billingAddress;

    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private OrderAddressEntity shippingAddress;

    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private OrderPaymentEntity payment;

}
