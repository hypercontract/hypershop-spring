package org.hypercontract.hypershop.orders;

import org.hypercontract.hypershop.orders.model.OrderItem;
import org.hypercontract.hypershop.orders.model.OrderPayment;
import org.hypercontract.hypershop.shoppingCart.ShoppingCartItem;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
interface OrderItemMapper {

    OrderItem toOrderItem(ShoppingCartItem shoppingCartItem);

    List<OrderItem> toOrderItems(List<ShoppingCartItem> shoppingCartItems);

}
