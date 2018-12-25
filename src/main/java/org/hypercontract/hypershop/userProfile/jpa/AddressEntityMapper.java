package org.hypercontract.hypershop.userProfile.jpa;

import org.hypercontract.hypershop.resource.IdMapper;
import org.hypercontract.hypershop.userProfile.Address;
import org.mapstruct.Mapper;

@Mapper(
    uses = IdMapper.class
)
public interface AddressEntityMapper {

    Address toAddress(AddressEntity addressEntity);

    AddressEntity toEntity(Address address);

}
