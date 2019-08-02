package org.hypercontract.hypershop.userProfile.mock;

import com.github.javafaker.Faker;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentOptionTestData {

    private static final int PAYMENT_OPTION_COUNT = 5;

    private List<PaymentOption> paymentOptions;
    private PaymentOption paymentOption;

    private PaymentOptionTestData(MockPaymentOptionBuilder mockPaymentOptionBuilder) {
        this.paymentOptions = mockPaymentOptionBuilder.buildMany()
            .limit(PAYMENT_OPTION_COUNT)
            .collect(Collectors.toUnmodifiableList());

        this.paymentOption = this.paymentOptions.get(0);
    }

    public List<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    private static PaymentOptionTestData instance;

    public static PaymentOptionTestData getInstance () {
        if (PaymentOptionTestData.instance == null) {
            PaymentOptionTestData.instance = new PaymentOptionTestData(
                new MockPaymentOptionBuilder(new Faker())
            );
        }
        return PaymentOptionTestData.instance;
    }
}
