package org.hypercontract.hypershop.product.mock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
class ProductDataInitializer implements InitializingBean {

    private static final int PRODUCT_COUNT = 100;

    private final MockProductBuilder mockProductBuilder;

    private final ProductDataInitializerRepository productDataInitializerRepository;

    @Override
    @Transactional
    public void afterPropertiesSet() {
        mockProductBuilder.buildMany()
            .limit(PRODUCT_COUNT)
            .forEach(product -> productDataInitializerRepository.save(product));
    }

}
