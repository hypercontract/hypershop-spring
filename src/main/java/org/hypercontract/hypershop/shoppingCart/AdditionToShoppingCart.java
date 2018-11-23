package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;

@Builder
@AllArgsConstructor
@ToString
public class AdditionToShoppingCart {

    @Getter
    private final Id<Product> productId;

    @Getter
    private final int quantity;

}
