package org.hypercontract.hypershop.orders;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartController;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        @PathVariable() Id<Order> orderId
    ) {
        return orderService.getById(orderId);
    }

    @GetMapping()
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> create(
        @RequestBody NewOrder newOrder
    ) {
        List<ShoppingCartItem> shoppingCartItems = newOrder.getItems().stream()
            .map(shoppingCartItemId -> shoppingCartController.getItemById(shoppingCartItemId))
            .collect(Collectors.toList());

        // TODO load via UserProfileController
        Address shippingAddress = Address.builder().build();
        Address billingAddress = Address.builder().build();
        PaymentOption payment = PaymentOption.builder().build();

        Order order = orderService.create(
            shoppingCartItems,
            shippingAddress,
            billingAddress,
            payment
        );

        URI orderUri = linkTo(methodOn(OrderController.class).getById(order.getId())).toUri();
        return ResponseEntity
            .created(orderUri)
            .build();
    }

}
