package org.hypercontract.hypershop.orders.jpa;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class OrderAddressEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String street;

    private String zipCode;

    private String city;

    private String country;

}
