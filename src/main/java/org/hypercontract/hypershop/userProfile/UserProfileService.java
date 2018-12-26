package org.hypercontract.hypershop.userProfile;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.resource.Id;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class UserProfileService {

    private final AddressRepository addressRepository;
    private final PaymentOptionRepository paymentOptionRepository;

    public UserProfile get() {
        return UserProfile.builder()
            .addresses(getAddresses())
            .paymentOptions(getPaymentOptions())
            .build();
    }

    public Address getAddressById(Id<Address> id) {
        return addressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    public PaymentOption getPaymentOptionById(Id<PaymentOption> id) {
        return paymentOptionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    private List<Address> getAddresses() {
        try(var addresses = addressRepository.findAll()) {
            return addresses
                .collect(Collectors.toList());
        }
    }

    private List<PaymentOption> getPaymentOptions() {
        try(var paymentOptions = paymentOptionRepository.findAll()) {
            return paymentOptions
                .collect(Collectors.toList());
        }
    }

}
