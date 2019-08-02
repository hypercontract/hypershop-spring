package org.hypercontract.hypershop.product.mock;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Component
@AllArgsConstructor
class ProductDataInitializer {

    private static final int PRODUCT_COUNT = 100;

    private final MockProductBuilder mockProductBuilder;

    private final ProductDataInitializerRepository productDataInitializerRepository;

    @EventListener
    @Order(HIGHEST_PRECEDENCE)
    @Transactional
    public void onContextRefreshed(ContextRefreshedEvent event) {
        mockProductBuilder.buildMany()
            .limit(PRODUCT_COUNT)
            .forEach(productDataInitializerRepository::save);
    }

}
