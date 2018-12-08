package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class QuantityUpdate {

    @Getter
    private final int quantity;

}
