package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.product.ProductController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/shoppingCart")
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductController productController;

    @GetMapping
    public ShoppingCart get() {
        return shoppingCartService.get();
    }

    @GetMapping("/items/{shoppingCartItemId}")
    public ShoppingCartItem getItemById(
        @PathVariable() ShoppingCartItem.ShoppingCartItemId shoppingCartItemId
    ) {
        return shoppingCartService.getItemById(shoppingCartItemId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/items")
    public ResponseEntity<Void> createItem(
        @RequestBody AdditionToShoppingCart additionToShoppingCart
    ) {
        var product = productController.getById(additionToShoppingCart.getProductId());

        ShoppingCartItem shoppingCartItem = shoppingCartService.createItem(additionToShoppingCart, product);

        URI orderUri = linkTo(methodOn(ShoppingCartController.class).getItemById(shoppingCartItem.getId())).toUri();
        return ResponseEntity
            .created(orderUri)
            .build();
    }

}
