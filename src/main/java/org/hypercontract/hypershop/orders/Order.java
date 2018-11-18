package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.ResourceId;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address.AddressId;
import org.hypercontract.hypershop.userProfile.PaymentOption.PaymentOptionId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Order {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final OrderId id = new OrderId();

    @Getter
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;

    @Getter
    private final List<OrderItem> items;

    @Getter
    private final AddressId billingAddress;

    @Getter
    private final AddressId shippingAddress;

    @Getter
    private final PaymentOptionId payment;

    @Getter
    private final LocalDateTime date;

    @NoArgsConstructor()
    public static final class OrderId extends ResourceId {
        public OrderId(String id) {
            super(id);
        }
    }

    public static class OrderBuilder {

        public OrderBuilder fromShoppingCart(ShoppingCart shoppingCart) {
            this.items = shoppingCart.getItems().stream()
                    .map(shoppingCartItem -> OrderItem.builder().fromShoppingCartItem(shoppingCartItem).build())
                    .collect(Collectors.toList());
            return this;
        }

    }
}
