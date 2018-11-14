package org.hypercontract.hypershop.userProfile;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@RequiredArgsConstructor
@ToString
public class PaymentOption {

    @Getter
    private final String accountOwner;

    @Getter
    private final String iban;

    @Getter
    private final String bic;

}
