package org.hypercontract.hypershop.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.resource.Id;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@ToString
public class Product {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
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
