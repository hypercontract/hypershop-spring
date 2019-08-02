package org.hypercontract.hypershop.shoppingCart.mock;

import org.hypercontract.hypershop.product.mock.ProductTestData;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartItemTestData {

    private static final int SHOPPING_CART_ITEM_COUNT = 3;

    private List<ShoppingCartItem> shoppingCartItems;
    private ShoppingCartItem shoppingCartItem;

    private ShoppingCartItemTestData() {
        this.shoppingCartItems = ProductTestData.getInstance().getProducts().stream()
            .limit(SHOPPING_CART_ITEM_COUNT)
            .map(product -> ShoppingCartItem.builder()
                .fromProduct(product)
                .quantity(1)
                .build())
            .collect(Collectors.toUnmodifiableList());

        this.shoppingCartItem = this.shoppingCartItems.get(0);
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public ShoppingCartItem getShoppingCartItem() {
        return shoppingCartItem;
    }

    private static ShoppingCartItemTestData instance;

    public static ShoppingCartItemTestData getInstance () {
        if (ShoppingCartItemTestData.instance == null) {
            ShoppingCartItemTestData.instance = new ShoppingCartItemTestData();
        }
        return ShoppingCartItemTestData.instance;
    }
}
