package org.hypercontract.hypershop.orders.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@ToString
public class OrderItem {

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final int quantity;

    @Getter
    private final Id<Product> product;

    public static class OrderItemBuilder {}
}
