package org.hypercontract.hypershop.userProfile;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@RequiredArgsConstructor
@ToString
public class UserProfile {

    @Getter
    private final List<Address> addresses;

    @Getter
    private final List<PaymentOption> paymentOptions;

}
