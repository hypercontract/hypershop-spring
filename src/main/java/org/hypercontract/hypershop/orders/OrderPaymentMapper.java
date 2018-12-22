package org.hypercontract.hypershop.orders;

import org.hypercontract.hypershop.orders.model.OrderPayment;
import org.hypercontract.hypershop.userProfile.PaymentOption;
import org.mapstruct.Mapper;

@Mapper
interface OrderPaymentMapper {

    OrderPayment toOrderPayment(PaymentOption paymentOption);

}
