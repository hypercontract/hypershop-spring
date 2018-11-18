package org.hypercontract.hypershop.orders;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartController;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.hypercontract.hypershop.orders.Order.OrderId;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartController shoppingCartController;

    @GetMapping("{orderId}")
    public Order getById(
        @PathVariable() OrderId orderId
    ) {
        return orderService.getById(orderId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public List<Order> getAll() {
        return orderService.findAll().collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> create(
        @RequestBody NewOrder newOrder
    ) {
        List<ShoppingCartItem> shoppingCartItems = newOrder.getItems().stream()
            .map(shoppingCartItemId -> shoppingCartController.getItemById(shoppingCartItemId))
            .collect(Collectors.toList());

        Order order = orderService.create(newOrder, shoppingCartItems);

        URI orderUri = linkTo(methodOn(OrderController.class).getById(order.getId())).toUri();
        return ResponseEntity
            .created(orderUri)
            .build();
    }

}
