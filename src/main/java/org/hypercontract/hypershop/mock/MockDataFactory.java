package org.hypercontract.hypershop.mock;

import lombok.RequiredArgsConstructor;
import org.hypercontract.hypershop.orders.OrderMockFactory;
import org.hypercontract.hypershop.product.ProductMockFactory;
import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartMockFactory;
import org.hypercontract.hypershop.userProfile.AddressMockFactory;
import org.hypercontract.hypershop.userProfile.PaymentOptionMockFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockDataFactory {

    private final ProductMockFactory productMockFactory;
    private final ShoppingCartMockFactory shoppingCartMockFactory;
    private final AddressMockFactory addressMockFactory;
    private final PaymentOptionMockFactory paymentOptionMockFactory;
    private final OrderMockFactory orderMockFactory;

    private final int addressCount = 3;
    private final int paymentOptionCount = 2;
    private final int productCount = 100;
    private final int orderCount = 100;
    private final int maxItemCount = 3;

    public MockData createMockData() {
        var addresses = addressMockFactory.createAddresses(addressCount);
        var paymentOptions = paymentOptionMockFactory.createPaymentOptions(paymentOptionCount);
        var products = productMockFactory.createProducts(productCount);
        var orders = orderMockFactory.createOrders(
            shoppingCartMockFactory.createShoppingCarts(products, orderCount, maxItemCount),
            addresses,
            paymentOptions
        );

        return MockData.builder()
            .addresses(addresses)
            .paymentOptions(paymentOptions)
            .products(products)
            .shoppingCart(new ShoppingCart())
            .orders(orders)
            .build();
    }
}
