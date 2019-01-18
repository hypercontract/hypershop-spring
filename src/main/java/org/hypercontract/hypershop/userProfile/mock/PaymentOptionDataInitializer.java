package org.hypercontract.hypershop.userProfile.mock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
class PaymentOptionDataInitializer implements InitializingBean {

    private static final int PAYMENT_OPTION_COUNT = 2;

    private final MockPaymentOptionBuilder mockPaymentOptionBuilder;

    private final PaymentOptionDataInitializerRepository paymentOptionDataInitializerRepository;

    @Override
    @Transactional
    public void afterPropertiesSet() {
        mockPaymentOptionBuilder.buildMany()
            .limit(PAYMENT_OPTION_COUNT)
            .forEach(paymentOption -> paymentOptionDataInitializerRepository.save(paymentOption));
    }


}
