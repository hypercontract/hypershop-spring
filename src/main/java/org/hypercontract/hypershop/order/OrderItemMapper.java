package org.hypercontract.hypershop.order;

import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItem(ShoppingCartItem shoppingCartItem);

    List<OrderItem> toOrderItems(List<ShoppingCartItem> shoppingCartItems);

}
