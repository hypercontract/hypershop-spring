package org.hypercontract.hypershop.shoppingCart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCartItem {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
    @javax.persistence.Id
    private Id<ShoppingCartItem> id = new Id();

    @Getter
    private String name;

    @Getter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Getter
    private BigDecimal price;

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int quantity;

    @Getter
    private Id<Product> product;

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
