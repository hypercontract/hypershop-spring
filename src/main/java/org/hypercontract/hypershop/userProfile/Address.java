package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;

@Builder
@AllArgsConstructor
@ToString
public class Address {

    @Getter
    @Builder.Default
    @JsonUnwrapped(prefix = "_")
    private final Id<Address> id = new Id();

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
