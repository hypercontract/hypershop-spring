package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.Id;

@Builder
@AllArgsConstructor
@ToString
public class PaymentOption {

    @Getter
    @Builder.Default
    @JsonUnwrapped(prefix = "_")
    private final Id<PaymentOption> id = new Id();

    @Getter
    private final String accountOwner;

    @Getter
    private final String iban;

    @Getter
    private final String bic;

}
