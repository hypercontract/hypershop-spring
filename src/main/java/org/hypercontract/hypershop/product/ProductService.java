package org.hypercontract.hypershop.product;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.product.jpa.ProductEntityMapper;
import org.hypercontract.hypershop.product.jpa.ProductRepository;
import org.hypercontract.hypershop.resource.Id;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
class ProductService {

    private final ProductRepository productRepository;

    private final ProductEntityMapper entityMapper = Mappers.getMapper(ProductEntityMapper.class);

    public Product getById(Id<Product> id) {
        return productRepository.findById(id.toString())
            .map(entityMapper::toProduct)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Product> findAll() {
        try(var products = productRepository.findAll()) {
            return products
                .map(entityMapper::toProduct)
                .collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findByQuery(String query) {
        try(var products = productRepository.findByNameContainingIgnoreCase(query)) {
            return products
                .map(entityMapper::toProduct)
                .collect(Collectors.toList());
        }
    }

}
