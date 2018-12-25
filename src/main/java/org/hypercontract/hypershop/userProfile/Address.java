package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.resource.Id;

@Builder
@AllArgsConstructor
@ToString
public class Address {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
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
