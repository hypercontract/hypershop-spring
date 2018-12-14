package org.hypercontract.hypershop.mock;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.orders.Order;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
public class MockData {

    @Getter
    private final List<Address> addresses;

    @Getter
    private final List<PaymentOption> paymentOptions;

    @Getter
    private final ShoppingCart shoppingCart;

    @Getter
    private final List<Order> orders;

}
