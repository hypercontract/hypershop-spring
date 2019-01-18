package org.hypercontract.hypershop.orders.mock;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.orders.NewOrder;
import org.hypercontract.hypershop.orders.OrderController;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.product.ProductController;
import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartController;
import org.hypercontract.hypershop.userProfile.UserProfileController;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@Component
@AllArgsConstructor
class OrderMockDataInitializer {

    private static final int ORDER_COUNT = 10;
    private static final int MAX_SHOPPING_CART_ITEM_COUNT = 3;

    private final ProductController productController;
    private final ShoppingCartController shoppingCartController;
    private final OrderController orderController;
    private final UserProfileController userProfileController;

    private final MockAdditionToShoppingCartBuilder mockAdditionToShoppingCartBuilder;
    private final MockNewOrderBuilder mockNewOrderBuilder;

    @EventListener
    @Order(LOWEST_PRECEDENCE)
    public void onContextRefreshed(ContextRefreshedEvent event) {
        var userProfile = userProfileController.get();
        var addresses = userProfile.getAddresses();
        var paymentOptions = userProfile.getPaymentOptions();

        var products = productController.getByQuery(Optional.empty());

        Stream.generate(() -> prepareShoppingCart(products, MAX_SHOPPING_CART_ITEM_COUNT))
            .map(shoppingCart -> mockNewOrderBuilder.build(shoppingCart, addresses, paymentOptions))
            .limit(ORDER_COUNT)
            .forEach(this::placeOrder);
    }

    private void placeOrder(NewOrder newOrder) {
        orderController.placeOrder(newOrder);
    }

    private ShoppingCart prepareShoppingCart(List<Product> products, int maxShoppingCartItemCount) {
        addRandomProductsToShoppingCart(
            products,
            getRandomShoppingCartItemCount(maxShoppingCartItemCount)
        );
        return shoppingCartController.get();
    }

    private void addRandomProductsToShoppingCart(List<Product> products, int shoppingCartItemCount) {
        mockAdditionToShoppingCartBuilder.buildMany(products)
            .limit(shoppingCartItemCount)
            .forEach(shoppingCartController::addItem);
    }

    private int getRandomShoppingCartItemCount(int maxShoppingCartItemCount) {
        Random random = new Random();
        return random.nextInt(maxShoppingCartItemCount) + 1;
    }

}
