package org.hypercontract.hypershop.userProfile;

import org.hypercontract.hypershop.resource.Id;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface PaymentOptionRepository extends Repository<PaymentOption, Id<PaymentOption>> {

    PaymentOption save(PaymentOption paymentOption);

    Optional<PaymentOption> findById(Id<PaymentOption> id);

    Stream<PaymentOption> findAll();

}
