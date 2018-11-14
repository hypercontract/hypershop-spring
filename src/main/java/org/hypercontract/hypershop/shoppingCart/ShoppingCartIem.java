package org.hypercontract.hypershop.shoppingCart;

import lombok.*;

@Builder
@RequiredArgsConstructor
@ToString
class ShoppingCartIem {

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final String price;

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private String quantity;

    @Getter
    private final String product;

}
