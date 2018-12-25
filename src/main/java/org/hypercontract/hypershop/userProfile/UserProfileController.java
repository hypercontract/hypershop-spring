package org.hypercontract.hypershop.userProfile;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userProfile")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfile get() {
        return userProfileService.get();
    }

    @GetMapping("/addresses/{addressId}")
    public Address getAddressById(
        @PathVariable() Id<Address> addressId
    ) {
        return userProfileService.getAddressById(addressId);
    }

    @GetMapping("/paymentOptions/{paymentOptionId}")
    public PaymentOption getPaymentOptionById(
        @PathVariable() Id<PaymentOption> paymentOptionId
    ) {
        return userProfileService.getPaymentOptionById(paymentOptionId);
    }

}
