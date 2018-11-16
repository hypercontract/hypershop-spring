package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.rest.ResourceId;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@ToString
public class OrderItem {

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
    private final int quantity;

    @Getter
    private final Product product;

    public static class OrderItemBuilder {

        public OrderItemBuilder fromShoppingCartItem(ShoppingCartItem shoppingCartItem) {
            this.name = shoppingCartItem.getName();
            this.description = shoppingCartItem.getDescription();
            this.price = shoppingCartItem.getPrice();
            this.quantity = shoppingCartItem.getQuantity();
            this.product = shoppingCartItem.getProduct();
            return this;
        }
    }
}
