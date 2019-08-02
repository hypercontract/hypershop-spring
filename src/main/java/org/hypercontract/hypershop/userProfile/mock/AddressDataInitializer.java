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
class AddressDataInitializer {

    private static final int ADDRESS_COUNT = 3;

    private final MockAddressBuilder mockAddressBuilder;

    private final AddressDataInitializerRepository addressDataInitializerRepository;

    @EventListener
    @Order(HIGHEST_PRECEDENCE)
    @Transactional
    public void onContextRefreshed(ContextRefreshedEvent event) {
        mockAddressBuilder.buildMany()
            .limit(ADDRESS_COUNT)
            .forEach(addressDataInitializerRepository::save);
    }


}
