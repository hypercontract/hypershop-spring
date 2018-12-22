package org.hypercontract.hypershop.orders.jpa;

import org.hypercontract.hypershop.orders.Order;
import org.hypercontract.hypershop.orders.OrderItem;
import org.hypercontract.hypershop.resource.IdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
