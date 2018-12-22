package org.hypercontract.hypershop.orders.model;

import org.hypercontract.hypershop.orders.jpa.OrderPaymentEntity;
import org.hypercontract.hypershop.resource.IdMapper;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.Mapper;

public interface OrderPaymentMapper {

    PaymentOption toOrderPayment(OrderPaymentEntity orderPaymentEntity);

    OrderPaymentEntity toEntity(PaymentOption orderPayment);

}
