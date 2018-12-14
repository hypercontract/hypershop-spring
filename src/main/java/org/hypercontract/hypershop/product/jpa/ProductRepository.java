package org.hypercontract.hypershop.product.jpa;

import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProductRepository extends Repository<ProductEntity, String> {

    ProductEntity save(ProductEntity product);

    Optional<ProductEntity> findById(String id);

    Stream<ProductEntity> findAll();

    Stream<ProductEntity> findByNameContainingIgnoreCase(String title);

}
