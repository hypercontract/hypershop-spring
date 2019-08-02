package org.hypercontract.hypershop.order.mock;

import org.hypercontract.hypershop.order.OrderPayment;
import org.hypercontract.hypershop.userProfile.mock.PaymentOptionTestData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderPaymentTestData {

    private static final int ORDER_PAYMENT_OPTION_COUNT = 2;

    private List<OrderPayment> orderPayments;
    private OrderPayment orderPayment;

    private OrderPaymentTestData() {
        this.orderPayments = PaymentOptionTestData.getInstance().getPaymentOptions().stream()
            .limit(ORDER_PAYMENT_OPTION_COUNT)
            .map(paymentOption -> OrderPayment.builder()
                .accountOwner(paymentOption.getAccountOwner())
                .bic(paymentOption.getBic())
                .iban(paymentOption.getIban())
                .build())
            .collect(Collectors.toUnmodifiableList());

        this.orderPayment = this.orderPayments.get(0);
    }

    public List<OrderPayment> getOrderPayments() {
        return orderPayments;
    }

    public OrderPayment getOrderPayment() {
        return orderPayment;
    }

    private static OrderPaymentTestData instance;

    public static OrderPaymentTestData getInstance () {
        if (OrderPaymentTestData.instance == null) {
            OrderPaymentTestData.instance = new OrderPaymentTestData();
        }
        return OrderPaymentTestData.instance;
    }
}
