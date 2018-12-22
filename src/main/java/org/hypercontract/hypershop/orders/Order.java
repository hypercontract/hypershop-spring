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
    @Builder.Default
    @JsonUnwrapped(prefix = "_")
    private final Id<Order> id = new Id();

    @Getter
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;

    @Getter
    private final LocalDateTime date;

    @Getter
    private final List<OrderItem> items;

    @Getter
    private final OrderAddress billingAddress;

    @Getter
    private final OrderAddress shippingAddress;

    @Getter
    private final OrderPayment payment;

    public static class OrderBuilder {

        public OrderBuilder fromShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
            this.items = shoppingCartItems.stream()
                .map(shoppingCartItem -> OrderItem.builder().fromShoppingCartItem(shoppingCartItem).build())
                .collect(Collectors.toList());
            return this;
        }

        public OrderBuilder fromBillingAddress(Address billingAddress) {
            this.billingAddress = OrderAddress.builder().fromAddress(billingAddress).build();
            return this;
        }

        public OrderBuilder fromShippingAddress(Address shippingAddress) {
            this.shippingAddress = OrderAddress.builder().fromAddress(shippingAddress).build();
            return this;
        }

        public OrderBuilder fromPaymentOption(PaymentOption payment) {
            this.payment = OrderPayment.builder().fromPaymentOption(payment).build();
            return this;
        }

    }
}
