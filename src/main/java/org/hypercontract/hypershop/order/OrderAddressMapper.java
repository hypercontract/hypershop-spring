package org.hypercontract.hypershop.order;

import org.hypercontract.hypershop.userProfile.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface OrderAddressMapper {

    @Mapping(target = "id", ignore = true)
    OrderAddress toOrderAddress(Address address);

}
