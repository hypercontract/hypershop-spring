package org.hypercontract.hypershop.product;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@RequiredArgsConstructor
@ToString
public class Product {

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final String image;

}
