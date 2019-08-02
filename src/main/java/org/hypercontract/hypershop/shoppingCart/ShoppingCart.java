package org.hypercontract.hypershop.shoppingCart;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCart {

    @Getter
    @Builder.Default
    private final List<ShoppingCartItem> items = new ArrayList<>();

    @JsonGetter("totalPrice")
    public BigDecimal getTotalPrice() {
        return items.stream()
            .map(ShoppingCartItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
