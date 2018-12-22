package org.hypercontract.hypershop.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.userProfile.PaymentOption;

@Builder
@AllArgsConstructor
@ToString
public class OrderPayment {

    @Getter
    private final String accountOwner;

    @Getter
    private final String iban;

    @Getter
    private final String bic;

    public static class OrderPaymentBuilder {}

}
