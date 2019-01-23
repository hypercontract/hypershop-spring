package org.hypercontract.hypershop.userProfile.mock;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
class MockPaymentOptionBuilder {

    private final Faker faker;

    public Stream<PaymentOption> buildMany() {
        return Stream.generate(this::buildPaymentOption);
    }

    private PaymentOption buildPaymentOption() {
        return PaymentOption.builder()
            .accountOwner(faker.name().name())
            .iban(faker.finance().iban())
            .bic(faker.finance().bic())
            .build();
    }

}
