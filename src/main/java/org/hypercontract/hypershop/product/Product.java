package org.hypercontract.hypershop.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@ToString
public class Product {

    @Getter
    @Builder.Default
    @JsonUnwrapped(prefix = "_")
    private final Id<Product> id = new Id();

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final String image;

}
