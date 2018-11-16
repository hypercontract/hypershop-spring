package org.hypercontract.hypershop.shoppingCart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hypercontract.hypershop.product.Product;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ShoppingCartItem {

    @Getter
    @JsonProperty("_id")
    private final String id;

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int quantity;

    @Getter
    private final Product product;

    public static class ShoppingCartItemBuilder {

        public ShoppingCartItemBuilder fromProduct(Product product) {
            this.name = product.getName();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.product = product;
            return this;
        }

        public ShoppingCartItemBuilder price(double price) {
            this.price = new BigDecimal(price);
            return this;
        }

    }
}
