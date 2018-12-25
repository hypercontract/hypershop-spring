package org.hypercontract.hypershop.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class StatusUpdate {

    @Getter
    private final OrderStatus status;

}
