package org.hypercontract.hypershop.orders.jpa;

import org.hypercontract.hypershop.orders.jpa.OrderPaymentEntity;
import org.hypercontract.hypershop.resource.IdMapper;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.Mapper;

@Mapper(
    uses = IdMapper.class
)
public interface OrderPaymentEntityMapper {

    PaymentOption toOrderPayment(OrderPaymentEntity orderPaymentEntity);

    OrderPaymentEntity toEntity(PaymentOption orderPayment);

}
