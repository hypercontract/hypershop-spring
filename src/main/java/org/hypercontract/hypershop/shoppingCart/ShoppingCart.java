package org.hypercontract.hypershop.shoppingCart;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
public class ShoppingCart {

    @Getter
    private final List<ShoppingCartItem> items;

}
