package org.hypercontract.hypershop.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderPayment {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @Getter
    private String accountOwner;

    @Getter
    private String iban;

    @Getter
    private String bic;

    public static class OrderPaymentBuilder {}

}
