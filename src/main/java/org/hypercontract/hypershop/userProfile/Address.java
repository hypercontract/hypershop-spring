package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;

import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
    @javax.persistence.Id
    private Id<Address> id = new Id<>();

    @Getter
    private String name;

    @Getter
    private String street;

    @Getter
    private String zipCode;

    @Getter
    private String city;

    @Getter
    private String country;

}
