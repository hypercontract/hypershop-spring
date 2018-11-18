package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.ResourceId;

@Builder
@AllArgsConstructor
@ToString
public class Address {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final AddressId id = new AddressId();

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

    @NoArgsConstructor
    public static final class AddressId extends ResourceId {
        public AddressId(String id) {
            super(id);
        }
    }

}
