package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hypercontract.hypershop.rest.ResourceId;

@Builder
@AllArgsConstructor
@ToString
public class PaymentOption {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final ResourceId id = new ResourceId();

    @Getter
    private final String accountOwner;

    @Getter
    private final String iban;

    @Getter
    private final String bic;

}
