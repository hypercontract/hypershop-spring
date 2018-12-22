package org.hypercontract.hypershop.orders.jpa;

import org.hypercontract.hypershop.orders.model.OrderItem;
import org.hypercontract.hypershop.resource.IdMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
    uses = IdMapper.class
)
public interface OrderItemEntityMapper {

    OrderItem toOrderItem(OrderItemEntity orderItemEntity);

    OrderItemEntity toEntity(OrderItem orderItem);

    List<OrderItem> toOrderItems(List<OrderItemEntity> orderItemEntities);

    List<OrderItemEntity> toEntities(List<OrderItem> orderItems);

}
