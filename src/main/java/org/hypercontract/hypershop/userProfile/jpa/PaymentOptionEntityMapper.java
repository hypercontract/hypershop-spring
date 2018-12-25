package org.hypercontract.hypershop.userProfile.jpa;

import org.hypercontract.hypershop.resource.IdMapper;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.Mapper;

@Mapper(
    uses = IdMapper.class
)
public interface PaymentOptionEntityMapper {

    PaymentOption toPaymentOption(PaymentOptionEntity paymentOptionEntity);

    PaymentOptionEntity toEntity(PaymentOption paymentOption);

}
