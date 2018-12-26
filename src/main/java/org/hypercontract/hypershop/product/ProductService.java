package org.hypercontract.hypershop.product;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ProductService {

    private final ProductRepository productRepository;

    public Product getById(Id<Product> id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Product> findAll() {
        try(var products = productRepository.findAll()) {
            return products
                .collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findByQuery(String query) {
        try(var products = productRepository.findByNameContainingIgnoreCase(query)) {
            return products
                .collect(Collectors.toList());
        }
    }

}
