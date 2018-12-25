package org.hypercontract.hypershop.userProfile.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class AddressEntity {

    @Id
    private String id;

    private String name;

    private String street;

    private String zipCode;

    private String city;

    private String country;

}
