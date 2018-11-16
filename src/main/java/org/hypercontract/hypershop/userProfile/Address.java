package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.rest.ResourceId;

@Builder
@AllArgsConstructor
@ToString
public class Address {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final ResourceId id = new ResourceId();

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
