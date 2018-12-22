package org.hypercontract.hypershop.orders.jpa;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class OrderItemEntity {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    private OrderEntity order;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;

    private int quantity;

    private String product;

}
