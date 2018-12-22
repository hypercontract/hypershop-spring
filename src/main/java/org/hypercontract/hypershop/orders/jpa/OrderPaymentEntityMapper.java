package org.hypercontract.hypershop.orders.jpa;

import org.hypercontract.hypershop.orders.model.OrderPayment;
import org.hypercontract.hypershop.resource.IdMapper;
import org.mapstruct.Mapper;

@Mapper(
    uses = IdMapper.class
)
public interface OrderPaymentEntityMapper {

    OrderPayment toOrderPayment(OrderPaymentEntity orderPaymentEntity);

    OrderPaymentEntity toEntity(OrderPayment orderPayment);

}
