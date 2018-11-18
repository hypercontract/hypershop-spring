package org.hypercontract.hypershop.shoppingCart;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCart {

    @Getter
    @Builder.Default
    private final List<ShoppingCartItem> items = List.of();

    @JsonGetter("totalPrice")
    public BigDecimal getTotalPrice() {
        return items.stream()
            .map(shoppingCartItem -> shoppingCartItem.getTotalPrice())
            .reduce( BigDecimal.ZERO, (totalPrice, price) -> totalPrice.add(price));
    }
}
