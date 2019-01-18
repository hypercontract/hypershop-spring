package org.hypercontract.hypershop.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
    @javax.persistence.Id
    private Id<Product> id = new Id();

    @Getter
    private String name;

    @Getter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Getter
    private BigDecimal price;

    @Getter
    private String image;

}
