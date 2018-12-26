package org.hypercontract.hypershop.product;

import org.hypercontract.hypershop.product.Product;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProductRepository extends Repository<Product, Id<Product>> {

    Product save(Product product);

    Optional<Product> findById(Id<Product> id);

    Stream<Product> findAll();

    Stream<Product> findByNameContainingIgnoreCase(String title);

}
