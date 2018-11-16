package org.hypercontract.hypershop.product;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductMockFactory {

    private final Faker faker = new Faker();

    public List<Product> createProducts(int productCount) {
        return Stream.generate(this::createProduct)
                .limit(productCount)
                .collect(Collectors.toList());
    }

    private Product createProduct() {
        return Product.builder()
                .name(faker.commerce().productName())
                .description(faker.lorem().paragraph())
                .price(createPrice())
                .image(createImage())
                .build();
    }

    private BigDecimal createPrice() {
        return new BigDecimal(faker.number().randomDouble(2, 0, 100));
    }

    private String createImage() {
        return faker.internet().image()
                .concat("?")
                .concat(faker.internet().uuid());
    }

}
