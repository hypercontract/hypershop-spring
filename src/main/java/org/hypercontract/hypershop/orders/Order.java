package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
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
    @JsonUnwrapped(prefix = "_")
    private final Id<Order> id = new Id();

    @Getter
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;

    @Getter
    private final List<OrderItem> items;

    @Getter
    private final Id<Address> billingAddress;

    @Getter
    private final Id<Address> shippingAddress;

    @Getter
    private final Id<PaymentOption> payment;

    @Getter
    private final LocalDateTime date;

    public static class OrderBuilder {

        public OrderBuilder fromNewOrder(NewOrder newOrder) {
            this.billingAddress = newOrder.getBillingAddress();
            this.shippingAddress = newOrder.getShippingAddress();
            this.payment = newOrder.getPayment();
            return this;
        }

        public OrderBuilder fromShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
            this.items = shoppingCartItems.stream()
                .map(shoppingCartItem -> OrderItem.builder().fromShoppingCartItem(shoppingCartItem).build())
                .collect(Collectors.toList());
            return this;
        }

    }
}
