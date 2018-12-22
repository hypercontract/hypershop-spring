package org.hypercontract.hypershop.orders;

import org.hypercontract.hypershop.orders.model.OrderAddress;
import org.hypercontract.hypershop.userProfile.Address;
import org.mapstruct.Mapper;

@Mapper
interface OrderAddressMapper {

    OrderAddress toOrderAddress(Address address);

}
