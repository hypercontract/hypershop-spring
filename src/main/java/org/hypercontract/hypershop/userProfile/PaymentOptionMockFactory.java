package org.hypercontract.hypershop.userProfile;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.userProfile.jpa.PaymentOptionEntityMapper;
import org.hypercontract.hypershop.userProfile.jpa.PaymentOptionRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PaymentOptionMockFactory {

    private final Faker faker = new Faker();

    private final PaymentOptionRepository paymentOptionRepository;

    private final PaymentOptionEntityMapper paymentOptionEntityMapper = Mappers.getMapper(PaymentOptionEntityMapper.class);

    public List<PaymentOption> createPaymentOptions(int paymentOptionCount) {
        return Stream.generate(this::createPaymentOption)
            .limit(paymentOptionCount)
            .peek(paymentOption -> paymentOptionRepository.save(
                paymentOptionEntityMapper.toEntity(paymentOption)
            ))
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
