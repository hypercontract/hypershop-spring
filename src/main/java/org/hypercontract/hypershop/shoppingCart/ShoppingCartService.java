package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.mock.MockData;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class ShoppingCartService {

    private final MockData mockData;

    public ShoppingCart get() {
        return mockData.getShoppingCart();
    }

    public Optional<ShoppingCartItem> getItemById(Id<ShoppingCartItem> id) {
        return mockData.getShoppingCart().getItems().stream()
            .parallel()
            .filter(shoppingCartItem -> shoppingCartItem.getId().toString().equals(id))
            .findAny();
    }

    public ShoppingCartItem createItem(AdditionToShoppingCart additionToShoppingCart, Product product) {
        ShoppingCartItem shoppingCartItem = ShoppingCartItem.builder()
            .fromProduct(product)
            .quantity(additionToShoppingCart.getQuantity())
            .build();

        mockData.getShoppingCart().getItems().add(shoppingCartItem);

        return shoppingCartItem;
    }

}
