package org.hypercontract.hypershop.userProfile.jpa;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface AddressRepository extends Repository<AddressEntity, String> {

    AddressEntity save(AddressEntity address);

    Optional<AddressEntity> findById(String id);

    Stream<AddressEntity> findAll();

}
