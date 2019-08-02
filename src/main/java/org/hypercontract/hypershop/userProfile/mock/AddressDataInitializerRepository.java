package org.hypercontract.hypershop.userProfile.mock;

import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.userProfile.Address;
import org.springframework.data.repository.Repository;

interface AddressDataInitializerRepository extends Repository<Address, Id<Address>> {

    void save(Address address);

}
