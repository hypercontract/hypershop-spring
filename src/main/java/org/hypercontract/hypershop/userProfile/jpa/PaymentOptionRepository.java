package org.hypercontract.hypershop.userProfile.jpa;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface PaymentOptionRepository extends Repository<PaymentOptionEntity, String> {

    PaymentOptionEntity save(PaymentOptionEntity paymentOption);

    Optional<PaymentOptionEntity> findById(String id);

    Stream<PaymentOptionEntity> findAll();

}
