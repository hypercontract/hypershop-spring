package org.hypercontract.hypershop.userProfile;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@RequiredArgsConstructor
@ToString
public class Address {

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

}
