package org.hypercontract.hypershop.userProfile;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;
import org.hypercontract.hypershop.resource.ResourceId;

@Builder
@AllArgsConstructor
@ToString
public class PaymentOption {

    @Getter
    @JsonUnwrapped(prefix = "_")
    private final PaymentOptionId id = new PaymentOptionId();

    @Getter
    private final String accountOwner;

    @Getter
    private final String iban;

    @Getter
    private final String bic;

    @NoArgsConstructor
    public static final class PaymentOptionId extends ResourceId {
        public PaymentOptionId(String id) {
            super(id);
        }
    }

}
