package org.hypercontract.hypershop.shoppingCart.mock;

import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartTestData {

    private ShoppingCart shoppingCart;

    private ShoppingCartTestData() {
        this.shoppingCart = ShoppingCart.builder()
            .items(ShoppingCartItemTestData.getInstance().getShoppingCartItems())
            .build();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    private static ShoppingCartTestData instance;

    public static ShoppingCartTestData getInstance () {
        if (ShoppingCartTestData.instance == null) {
            ShoppingCartTestData.instance = new ShoppingCartTestData();
        }
        return ShoppingCartTestData.instance;
    }
}
