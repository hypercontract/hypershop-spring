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
public class PaymentOption {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
    @javax.persistence.Id
    private Id<PaymentOption> id = new Id();

    @Getter
    private String accountOwner;

    @Getter
    private String iban;

    @Getter
    private String bic;

}
