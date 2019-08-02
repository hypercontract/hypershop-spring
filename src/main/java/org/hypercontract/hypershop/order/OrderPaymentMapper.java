package org.hypercontract.hypershop.order;

import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface OrderPaymentMapper {

    @Mapping(target = "id", ignore = true)
    OrderPayment toOrderPayment(PaymentOption paymentOption);

}
