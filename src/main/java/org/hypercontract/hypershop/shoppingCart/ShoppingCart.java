package org.hypercontract.hypershop.shoppingCart;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@RequiredArgsConstructor
@ToString
public class ShoppingCart {

    @Getter
    private final List<ShoppingCartIem> items;

}
