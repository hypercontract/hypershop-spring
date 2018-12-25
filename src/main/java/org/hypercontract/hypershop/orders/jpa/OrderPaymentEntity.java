package org.hypercontract.hypershop.orders.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class OrderPaymentEntity {

    @Id
    @GeneratedValue
    private long id;

    private String accountOwner;

    private String iban;

    private String bic;

}
