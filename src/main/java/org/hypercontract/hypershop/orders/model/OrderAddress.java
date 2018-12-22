package org.hypercontract.hypershop.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.userProfile.Address;

@Builder
@AllArgsConstructor
@ToString
public class OrderAddress {

    @Getter
    private final String name;

    @Getter
    private final String street;

    @Getter
    private final String zipCode;

    @Getter
    private final String city;

    @Getter
    private final String country;

    public static class OrderAddressBuilder {}

}
