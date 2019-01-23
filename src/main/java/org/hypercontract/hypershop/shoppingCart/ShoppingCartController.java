package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.product.ProductController;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        @PathVariable() Id<ShoppingCartItem> shoppingCartItemId
    ) {
        return shoppingCartService.getItemById(shoppingCartItemId);
    }

    @PostMapping("/items")
    public ResponseEntity<Void> addItem(
        @RequestBody AdditionToShoppingCart additionToShoppingCart
    ) {
        var product = productController.getById(additionToShoppingCart.getProduct());

        shoppingCartService.addItem(additionToShoppingCart, product);

        URI shoppingCartUri = linkTo(methodOn(ShoppingCartController.class).get()).toUri();

        return ResponseEntity
            .created(shoppingCartUri)
            .build();
    }

    @PatchMapping("/items/{shoppingCartItemId}")
    public ShoppingCart updateItemQuantity(
        @PathVariable() Id<ShoppingCartItem> shoppingCartItemId,
        @RequestBody QuantityUpdate quantityUpdate
    ) {
        shoppingCartService.updateItemQuantity(shoppingCartItemId, quantityUpdate);
        return get();
    }

    @DeleteMapping("/items/{shoppingCartItemId}")
    public ShoppingCart removeItem(
        @PathVariable() Id<ShoppingCartItem> shoppingCartItemId
    ) {
        shoppingCartService.removeItem(shoppingCartItemId);
        return get();
    }

}
