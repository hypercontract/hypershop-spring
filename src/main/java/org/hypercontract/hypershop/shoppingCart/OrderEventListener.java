package org.hypercontract.hypershop.shoppingCart;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.orders.OrderPlaced;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEventListener {

    private final ShoppingCartService shoppingCartService;

    @Async
    @EventListener
    public void onOrderPlaced(OrderPlaced orderPlaced) {
        shoppingCartService.emptyShoppingCart();
    }

}
