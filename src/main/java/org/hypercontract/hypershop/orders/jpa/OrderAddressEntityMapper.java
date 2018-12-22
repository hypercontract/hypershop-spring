package org.hypercontract.hypershop.orders.jpa;

import org.hypercontract.hypershop.resource.IdMapper;
import org.hypercontract.hypershop.userProfile.Address;
import org.mapstruct.Mapper;

@Mapper(
    uses = IdMapper.class
)
public interface OrderAddressEntityMapper {

    Address toOrderAddress(OrderAddressEntity orderAddressEntity);

    OrderAddressEntity toEntity(Address orderAddress);

}
