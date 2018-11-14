package org.hypercontract.hypershop.orders;

import lombok.*;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@RequiredArgsConstructor
@ToString
class Order {

    @Getter
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

}
