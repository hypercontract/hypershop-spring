package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.product.Product.ProductId;

@Builder
@AllArgsConstructor
@ToString
public class AdditionToShoppingCart {

    @Getter
    private final ProductId productId;

    @Getter
    private final int quantity;

}
