package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@ToString
public class Address {

    @Getter
    @JsonProperty("_id")
    private final String id;

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
