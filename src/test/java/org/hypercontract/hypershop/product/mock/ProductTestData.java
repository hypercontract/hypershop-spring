package org.hypercontract.hypershop.product.mock;

import com.github.javafaker.Faker;
import org.hypercontract.hypershop.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductTestData {

    private static final int PRODUCT_COUNT = 5;

    private List<Product> products;
    private Product product;

    private ProductTestData(MockProductBuilder mockProductBuilder) {
        this.products = mockProductBuilder.buildMany()
            .limit(PRODUCT_COUNT)
            .collect(Collectors.toUnmodifiableList());

        this.product = this.products.get(0);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct() {
        return product;
    }

    private static ProductTestData instance;

    public static ProductTestData getInstance () {
        if (ProductTestData.instance == null) {
            ProductTestData.instance = new ProductTestData(
                new MockProductBuilder(new Faker())
            );
        }
        return ProductTestData.instance;
    }
}
