package org.hypercontract.hypershop.orders;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.mock.MockData;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
class OrderService {

    private final MockData mockData;

    public Optional<Order> getById(Id<Order> id) {
        return mockData.getOrders().stream()
            .parallel()
            .filter(order -> order.getId().equals(id))
            .findAny();
    }

    public Stream<Order> findAll() {
        return mockData.getOrders().stream();
    }

    public Order create(NewOrder newOrder, List<ShoppingCartItem> shoppingCartItems) {
        Order order = Order.builder()
            .fromNewOrder(newOrder)
            .fromShoppingCartItems(shoppingCartItems)
            .build();

        mockData.getOrders().add(order);

        return order;
    }

}
