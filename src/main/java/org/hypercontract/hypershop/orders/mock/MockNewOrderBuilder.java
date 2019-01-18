package org.hypercontract.hypershop.orders.mock;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.orders.NewOrder;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class MockNewOrderBuilder {

    public NewOrder build(ShoppingCart shoppingCart, List<Address> addresses, List<PaymentOption> paymentOptions) {
        return NewOrder.builder()
            .items(getShoppingCartItemIds(shoppingCart))
            .billingAddress(getRandomAddressId(addresses))
            .shippingAddress(getRandomAddressId(addresses))
            .payment(getRandomPaymentOptionId(paymentOptions))
            .build();
    }

    private List<Id<ShoppingCartItem>> getShoppingCartItemIds(ShoppingCart shoppingCart) {
        return shoppingCart.getItems()
            .stream()
            .map(item -> item.getId())
            .collect(Collectors.toList());
    }

    private Id<Address> getRandomAddressId(List<Address> address) {
        Random random = new Random();
        var index = random.nextInt(address.size());
        return address.get(index).getId();
    }

    private Id<PaymentOption> getRandomPaymentOptionId(List<PaymentOption> paymentOption) {
        Random random = new Random();
        var index = random.nextInt(paymentOption.size());
        return paymentOption.get(index).getId();
    }

}
