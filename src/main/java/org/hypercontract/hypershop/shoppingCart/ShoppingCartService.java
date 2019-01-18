package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.mock.MockData;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
class ShoppingCartService {

    private final MockData mockData;

    public ShoppingCart get() {
        return mockData.getShoppingCart();
    }

    public Optional<ShoppingCartItem> findItemById(Id<ShoppingCartItem> id) {
        return findByPredicate(shoppingCartItem -> shoppingCartItem.getId().equals(id));
    }

    public ShoppingCartItem getItemById(Id<ShoppingCartItem> id) {
        return this.findItemById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    public ShoppingCartItem addItem(AdditionToShoppingCart additionToShoppingCart, Product product) {
        var existingItem = findByProduct(product.getId());

        existingItem.ifPresent(item -> increaseItemQuantity(item, additionToShoppingCart.getQuantity()));

        return existingItem
            .orElseGet(() -> createItem(additionToShoppingCart, product));
    }

    public ShoppingCartItem updateItemQuantity(Id<ShoppingCartItem> id, QuantityUpdate quantityUpdate) {
        var shoppingCartItem = getItemById(id);
        return updateItemQuantity(shoppingCartItem, quantityUpdate.getQuantity());
    }

    public void removeItem(Id<ShoppingCartItem> id) {
        var shoppingCartItem = getItemById(id);
        removeItem(shoppingCartItem);
    }

    private ShoppingCartItem createItem(AdditionToShoppingCart additionToShoppingCart, Product product) {
        ShoppingCartItem shoppingCartItem = ShoppingCartItem.builder()
                .fromProduct(product)
                .quantity(additionToShoppingCart.getQuantity())
                .build();

        mockData.getShoppingCart().getItems().add(shoppingCartItem);

        return shoppingCartItem;
    }

    private ShoppingCartItem increaseItemQuantity(ShoppingCartItem shoppingCartItem, int quantityIncrease) {
        return this.updateItemQuantity(
            shoppingCartItem,
            shoppingCartItem.getQuantity() + quantityIncrease
        );
    }

    private ShoppingCartItem updateItemQuantity(ShoppingCartItem shoppingCartItem, int quantity) {
        shoppingCartItem.setQuantity(quantity);
        return shoppingCartItem;
    }

    private void removeItem(ShoppingCartItem shoppingCartItem) {
        mockData.getShoppingCart().getItems().remove(shoppingCartItem);
    }

    private Optional<ShoppingCartItem> findByProduct(Id<Product> productId) {
        return findByPredicate(
            shoppingCartItem -> shoppingCartItem.getProduct().equals(productId)
        );
    }

    private Optional<ShoppingCartItem> findByPredicate(Predicate<ShoppingCartItem> predicate) {
        return mockData.getShoppingCart().getItems().stream()
                .parallel()
                .filter(predicate)
                .findAny();
    }

}
