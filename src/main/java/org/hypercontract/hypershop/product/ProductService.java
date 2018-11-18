package org.hypercontract.hypershop.product;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.mock.MockData;
import org.hypercontract.hypershop.product.Product.ProductId;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
class ProductService {

    private final MockData mockData;

    public Optional<Product> getById(ProductId id) {
        return mockData.getProducts().stream()
            .parallel()
            .filter(product -> product.getId().equals(id))
            .findAny();
    }

    public Stream<Product> findAll() {
        return mockData.getProducts().stream();
    }

    public Stream<Product> findByQuery(String query) {
        return this.findAll()
            .filter(product -> matchesQuery(product, query));
    }

    private boolean matchesQuery(Product product, String query) {
        return (
            product.getName().toLowerCase().contains(query) ||
                product.getDescription().toLowerCase().contains(query)
        );
    }

}
