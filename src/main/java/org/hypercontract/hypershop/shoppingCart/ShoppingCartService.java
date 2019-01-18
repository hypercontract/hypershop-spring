package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ShoppingCartService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Transactional(readOnly = true)
    public ShoppingCart get() {
        return ShoppingCart.builder()
            .items(findAllItems())
            .build();
    }

    public ShoppingCartItem getItemById(Id<ShoppingCartItem> id) {
        return findItemById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional()
    public ShoppingCartItem addItem(AdditionToShoppingCart additionToShoppingCart, Product product) {
        var existingItem = shoppingCartItemRepository.findByProduct(product.getId());

        existingItem.ifPresent(item -> increaseItemQuantity(item, additionToShoppingCart.getQuantity()));

        return existingItem
            .orElseGet(() -> createItem(additionToShoppingCart, product));
    }

    @Transactional()
    public ShoppingCartItem updateItemQuantity(Id<ShoppingCartItem> id, QuantityUpdate quantityUpdate) {
        var shoppingCartItem = getItemById(id);
        return updateItemQuantity(shoppingCartItem, quantityUpdate.getQuantity());
    }

    @Transactional()
    public void removeItem(Id<ShoppingCartItem> id) {
        shoppingCartItemRepository.deleteById(id);
    }

    private List<ShoppingCartItem> findAllItems() {
        try(var shoppingCartItem = shoppingCartItemRepository.findAll()) {
            return shoppingCartItem
                .collect(Collectors.toList());
        }
    }

    private Optional<ShoppingCartItem> findItemById(Id<ShoppingCartItem> id) {
        return shoppingCartItemRepository.findById(id);
    }

    private ShoppingCartItem createItem(AdditionToShoppingCart additionToShoppingCart, Product product) {
        ShoppingCartItem shoppingCartItem = ShoppingCartItem.builder()
                .fromProduct(product)
                .quantity(additionToShoppingCart.getQuantity())
                .build();

        return shoppingCartItemRepository.save(shoppingCartItem);
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


}
