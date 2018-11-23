package org.hypercontract.hypershop.shoppingCart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ShoppingCartItem {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final Id<ShoppingCartItem> id = new Id();

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
    private final Id<Product> product;

    @JsonIgnore
    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(quantity));
    }

    public static class ShoppingCartItemBuilder {

        public ShoppingCartItemBuilder fromProduct(Product product) {
            this.name = product.getName();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.product = product.getId();
            return this;
        }

        public ShoppingCartItemBuilder price(double price) {
            this.price = new BigDecimal(price);
            return this;
        }

    }
}
