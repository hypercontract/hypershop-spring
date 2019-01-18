package org.hypercontract.hypershop.product.mock;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.product.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
class MockProductBuilder {

    private final Faker faker;

    public Stream<Product> buildMany() {
        return Stream.generate(this::build);
    }

    private Product build() {
        return Product.builder()
            .name(faker.commerce().productName())
            .description(faker.lorem().paragraph())
            .price(getRandomPrice())
            .image(getRandomImage())
            .build();
    }

    private BigDecimal getRandomPrice() {
        return new BigDecimal(faker.number().randomDouble(2, 0, 100));
    }

    private String getRandomImage() {
        return faker.internet().image()
            .concat("?")
            .concat(faker.internet().uuid());
    }

}
