package org.hypercontract.hypershop.orders.jpa;

import org.hypercontract.hypershop.orders.model.Order;
import org.hypercontract.hypershop.resource.IdMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    uses = { IdMapper.class, OrderItemEntityMapper.class }
)
public abstract class OrderEntityMapper {

    public abstract Order toOrder(OrderEntity orderEntity);

    public abstract OrderEntity toEntity(Order order);

    @AfterMapping
    protected void setOrderOnOrderItems(Order order, @MappingTarget OrderEntity orderEntity) {
        orderEntity.getItems()
            .forEach(item -> item.setOrder(orderEntity));
    }

}
