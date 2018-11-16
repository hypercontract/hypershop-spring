package org.hypercontract.hypershop.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.rest.ResourceId;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@ToString
public class Product {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final ResourceId id = new ResourceId();

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final String image;

}
