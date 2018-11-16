package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hypercontract.hypershop.shoppingCart.ShoppingCart;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Order {

    @Getter
    @JsonProperty("_id")
    private final String id;

    @Getter
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;

    @Getter
    private final List<OrderItem> items;

    @Getter
    private final Address billingAddress;

    @Getter
    private final Address shippingAddress;

    @Getter
    private final PaymentOption payment;

    @Getter
    private final LocalDateTime date;

    public static class OrderBuilder {

        public OrderBuilder fromShoppingCart(ShoppingCart shoppingCart) {
            this.items = shoppingCart.getItems().stream()
                    .map(shoppingCartItem -> OrderItem.builder().fromShoppingCartItem(shoppingCartItem).build())
                    .collect(Collectors.toList());
            return this;
        }

    }
}
