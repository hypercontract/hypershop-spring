package org.hypercontract.hypershop.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
public class NewOrder {

    @Getter
    private final List<Id<ShoppingCartItem>> items;

    @Getter
    private final Id<Address> billingAddress;

    @Getter
    private final Id<Address> shippingAddress;

    @Getter
    private final Id<PaymentOption> payment;

}
