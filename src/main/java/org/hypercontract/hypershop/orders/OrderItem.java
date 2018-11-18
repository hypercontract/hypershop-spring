package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.product.Product.ProductId;
import org.hypercontract.hypershop.resource.ResourceId;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@ToString
public class OrderItem {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final OrderItemId id = new OrderItemId();

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final int quantity;

    @Getter
    private final ProductId product;

    @NoArgsConstructor
    public static final class OrderItemId extends ResourceId {
        public OrderItemId(String id) {
            super(id);
        }
    }

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
