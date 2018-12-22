package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.resource.Id;
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

    public static class OrderAddressBuilder {

        public OrderAddress.OrderAddressBuilder fromAddress(Address address) {
            this.name = address.getName();
            this.street = address.getStreet();
            this.zipCode = address.getZipCode();
            this.city = address.getCity();
            this.country = address.getCountry();
            return this;
        }
    }

}
