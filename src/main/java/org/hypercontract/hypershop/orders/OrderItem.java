package org.hypercontract.hypershop.orders;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hypercontract.hypershop.product.Product;

import java.math.BigDecimal;

@Builder
@RequiredArgsConstructor
@ToString
class OrderItem {

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final BigDecimal quantity;

    @Getter
    private final Product product;

}
