package org.hypercontract.hypershop.userProfile.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class PaymentOptionEntity {

    @Id
    private String id;

    private String accountOwner;

    private String iban;

    private String bic;

}
