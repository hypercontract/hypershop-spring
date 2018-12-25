package org.hypercontract.hypershop.orders.jpa;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrderRepository extends Repository<OrderEntity, String> {

    OrderEntity save(OrderEntity order);

    Optional<OrderEntity> findById(String id);

    Stream<OrderEntity> findAll();

}
