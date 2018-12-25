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
public class PaymentOption {

    @Getter
    @Builder.Default
    @JsonProperty("_id")
    private final Id<PaymentOption> id = new Id();

    @Getter
    private final String accountOwner;

    @Getter
    private final String iban;

    @Getter
    private final String bic;

}
