package org.hypercontract.hypershop.shoppingCart;

import com.github.javafaker.Faker;
import org.hypercontract.hypershop.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShoppingCartMockFactory {

    private final Faker faker = new Faker();

    public List<ShoppingCart> createShoppingCarts(List<Product> products, int shoppingCartCount, int maxShoppingCartItemCount) {
        return Stream.generate(() -> {
            var shoppingCartItemCount = faker.number().numberBetween(1, maxShoppingCartItemCount + 1);
            return createShoppingCart(products, shoppingCartItemCount);
        })
                .limit(shoppingCartCount)
                .collect(Collectors.toList());
    }

    private ShoppingCart createShoppingCart(List<Product> products, int shoppingCartItemCount) {
        return ShoppingCart.builder()
                .items(createShoppingCartItems(products, shoppingCartItemCount))
                .build();
    }

    private List<ShoppingCartItem> createShoppingCartItems(List<Product> products, int shoppingCartItemCount) {
        return Stream.generate(() -> createShoppingCartItem(getRandomProduct(products)))
                .limit(shoppingCartItemCount)
                .collect(Collectors.toList());
    }

    private ShoppingCartItem createShoppingCartItem(Product product) {
        return ShoppingCartItem.builder()
                .fromProduct(product)
                .quantity(faker.number().numberBetween(1, 6))
                .build();
    }

    private Product getRandomProduct(List<Product> products) {
        Random random = new Random();
        var index = random.nextInt(products.size());
        return products.get(index);
    }

}
