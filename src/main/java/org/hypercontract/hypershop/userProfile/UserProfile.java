package org.hypercontract.hypershop.userProfile;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@ToString
public class UserProfile {

    @Getter
    private final List<Address> addresses;

    @Getter
    private final List<PaymentOption> paymentOptions;

}
