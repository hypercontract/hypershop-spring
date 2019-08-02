package org.hypercontract.hypershop.order.mock;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.shoppingCart.AdditionToShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
class MockAdditionToShoppingCartBuilder {

    private final Faker faker;

    public Stream<AdditionToShoppingCart> buildMany(List<Product> products) {
        return Stream.generate(() -> build(getRandomProduct(products)));
    }

    private AdditionToShoppingCart build(Product product) {
        return AdditionToShoppingCart.builder()
            .product(product.getId())
            .quantity(faker.number().numberBetween(1, 6))
            .build();
    }

    private Product getRandomProduct(List<Product> products) {
        Random random = new Random();
        var index = random.nextInt(products.size());
        return products.get(index);
    }

}
