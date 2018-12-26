package org.hypercontract.hypershop.userProfile;

import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface AddressRepository extends Repository<Address, Id<Address>> {

    Address save(Address address);

    Optional<Address> findById(Id<Address> id);

    Stream<Address> findAll();

}
