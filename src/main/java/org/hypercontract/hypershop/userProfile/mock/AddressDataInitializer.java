package org.hypercontract.hypershop.userProfile.mock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
class AddressDataInitializer implements InitializingBean {

    private static final int ADDRESS_COUNT = 3;

    private final MockAddressBuilder mockAddressBuilder;

    private final AddressDataInitializerRepository addressDataInitializerRepository;

    @Override
    @Transactional
    public void afterPropertiesSet() {
        mockAddressBuilder.buildMany()
            .limit(ADDRESS_COUNT)
            .forEach(address -> addressDataInitializerRepository.save(address));
    }


}
