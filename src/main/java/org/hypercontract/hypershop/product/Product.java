package org.hypercontract.hypershop.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.ResourceId;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@ToString
public class Product {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final ProductId id = new ProductId();

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final String image;

    @NoArgsConstructor
    public static final class ProductId extends ResourceId {
        public ProductId(String id) {
            super(id);
        }
    }

}
