package org.hypercontract.hypershop.shoppingCart;

import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ShoppingCartItemRepository extends Repository<ShoppingCartItem, Id<ShoppingCartItem>> {

    ShoppingCartItem save(ShoppingCartItem shoppingCartItem);

    Optional<ShoppingCartItem> findById(Id<ShoppingCartItem> id);

    Optional<ShoppingCartItem> findByProduct(Id<Product> productId);

    Stream<ShoppingCartItem> findAll();

    void deleteById(Id<ShoppingCartItem> id);

}
