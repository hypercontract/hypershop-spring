package org.hypercontract.hypershop.userProfile;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PaymentOptionMockFactory {

    private final Faker faker = new Faker();

    private final PaymentOptionRepository paymentOptionRepository;

    public List<PaymentOption> createPaymentOptions(int paymentOptionCount) {
        return Stream.generate(this::createPaymentOption)
            .limit(paymentOptionCount)
            .peek(paymentOptionRepository::save)
            .collect(Collectors.toList());
    }

    private PaymentOption createPaymentOption() {
        return PaymentOption.builder()
            .accountOwner(faker.name().name())
            .iban(faker.finance().iban())
            .bic(faker.finance().bic())
            .build();
    }

}
