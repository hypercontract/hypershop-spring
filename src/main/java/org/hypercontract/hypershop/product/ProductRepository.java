package org.hypercontract.hypershop.product;

import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

interface ProductRepository extends Repository<Product, Id<Product>> {

    Optional<Product> findById(Id<Product> id);

    Stream<Product> findAll();

    Stream<Product> findByNameContainingIgnoreCase(String title);

}
