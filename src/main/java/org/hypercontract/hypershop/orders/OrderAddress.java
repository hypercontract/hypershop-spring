package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hypercontract.hypershop.userProfile.Address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderAddress {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

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

    public static class OrderAddressBuilder {}

}