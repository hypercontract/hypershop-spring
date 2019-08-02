package org.hypercontract.hypershop.order;

import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrderRepository extends Repository<Order, Id<Order>> {

    Order save(Order order);

    Optional<Order> findById(Id<Order> id);

    Stream<Order> findAll();

}
