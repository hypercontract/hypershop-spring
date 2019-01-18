package org.hypercontract.hypershop.userProfile.mock;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Component
@AllArgsConstructor
class PaymentOptionDataInitializer {

    private static final int PAYMENT_OPTION_COUNT = 2;

    private final MockPaymentOptionBuilder mockPaymentOptionBuilder;

    private final PaymentOptionDataInitializerRepository paymentOptionDataInitializerRepository;

    @EventListener
    @Order(HIGHEST_PRECEDENCE)
    @Transactional
    public void onContextRefreshed(ContextRefreshedEvent event) {
        mockPaymentOptionBuilder.buildMany()
            .limit(PAYMENT_OPTION_COUNT)
            .forEach(paymentOption -> paymentOptionDataInitializerRepository.save(paymentOption));
    }


}
