package org.hypercontract.hypershop.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem.ShoppingCartItemId;
import org.hypercontract.hypershop.userProfile.Address.AddressId;
import org.hypercontract.hypershop.userProfile.PaymentOption.PaymentOptionId;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
public class NewOrder {

    @Getter
    private final List<ShoppingCartItemId> items;

    @Getter
    private final AddressId billingAddress;

    @Getter
    private final AddressId shippingAddress;

    @Getter
    private final PaymentOptionId payment;

}
