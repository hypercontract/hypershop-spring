package org.hypercontract.hypershop.userProfile.mock;

import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.springframework.data.repository.Repository;

interface PaymentOptionDataInitializerRepository extends Repository<PaymentOption, Id<PaymentOption>> {

    PaymentOption save(PaymentOption paymentOption);

}
