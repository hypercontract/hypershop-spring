package org.hypercontract.hypershop.orders;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.http.RequestBodyMapping;
import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartController;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.Address;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.hypercontract.hypershop.userProfile.UserProfileController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final UserProfileController userProfileController;

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
    public ResponseEntity<Void> placeOrder(
        @RequestBody NewOrder newOrder
    ) {
        List<ShoppingCartItem> shoppingCartItems = newOrder.getItems().stream()
            .map(shoppingCartItemId -> shoppingCartController.getItemById(shoppingCartItemId))
            .collect(Collectors.toList());

        Address shippingAddress = userProfileController.getAddressById(newOrder.getShippingAddress());
        Address billingAddress = userProfileController.getAddressById(newOrder.getBillingAddress());
        PaymentOption payment = userProfileController.getPaymentOptionById(newOrder.getPayment());

        Order order = orderService.placeOrder(
            shoppingCartItems,
            shippingAddress,
            billingAddress,
            payment
        );

        return getRedirection(HttpStatus.CREATED, order.getId());
    }

    @PatchMapping("{orderId}")
    @RequestBodyMapping(value = StatusUpdate.class, condition = "status.is('Cancelled')")
    public ResponseEntity<Void> cancelOrder(
        @PathVariable Id<Order> orderId
    ) {

        Order order = orderService.cancelOrder(orderId);
        return getRedirection(HttpStatus.SEE_OTHER, order.getId());
    }

    @PatchMapping("{orderId}")
    @RequestBodyMapping(value = StatusUpdate.class, condition = "status.is('Returned')")
    public ResponseEntity<Void> returnOrder(
        @PathVariable Id<Order> orderId
    ) {
        Order order = orderService.returnOrder(orderId);
        return getRedirection(HttpStatus.SEE_OTHER, order.getId());
    }

    private ResponseEntity<Void> getRedirection(HttpStatus status, Id<Order> orderId) {
        URI orderUri = linkTo(methodOn(OrderController.class).getById(orderId)).toUri();

        return ResponseEntity
            .status(status)
            .location(orderUri)
            .build();
    }

}
