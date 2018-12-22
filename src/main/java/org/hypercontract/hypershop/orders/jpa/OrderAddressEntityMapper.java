package org.hypercontract.hypershop.orders.jpa;

import org.hypercontract.hypershop.orders.model.OrderAddress;
import org.hypercontract.hypershop.resource.IdMapper;
import org.mapstruct.Mapper;

@Mapper(
    uses = IdMapper.class
)
public interface OrderAddressEntityMapper {

    OrderAddress toOrderAddress(OrderAddressEntity orderAddressEntity);

    OrderAddressEntity toEntity(OrderAddress orderAddress);

}
