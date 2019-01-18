package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem {

    @javax.persistence.Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @Getter
    private String name;

    @Getter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Getter
    private BigDecimal price;

    @Getter
    private int quantity;

    @Getter
    private Id<Product> product;

    public static class OrderItemBuilder {}
}
