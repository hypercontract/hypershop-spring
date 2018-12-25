package org.hypercontract.hypershop.userProfile;

import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.userProfile.jpa.AddressEntityMapper;
import org.hypercontract.hypershop.userProfile.jpa.AddressRepository;
import org.hypercontract.hypershop.userProfile.jpa.PaymentOptionEntityMapper;
import org.hypercontract.hypershop.userProfile.jpa.PaymentOptionRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class UserProfileService {

    private final AddressRepository addressRepository;
    private final AddressEntityMapper addressEntityMapper = Mappers.getMapper(AddressEntityMapper.class);

    private final PaymentOptionRepository paymentOptionRepository;
    private final PaymentOptionEntityMapper paymentOptionEntityMapper = Mappers.getMapper(PaymentOptionEntityMapper.class);

    public UserProfile get() {
        return UserProfile.builder()
            .addresses(getAddresses())
            .paymentOptions(getPaymentOptions())
            .build();
    }

    private List<Address> getAddresses() {
        try(var addresses = addressRepository.findAll()) {
            return addresses
                .map(addressEntityMapper::toAddress)
                .collect(Collectors.toList());
        }
    }

    private List<PaymentOption> getPaymentOptions() {
        try(var paymentOptions = paymentOptionRepository.findAll()) {
            return paymentOptions
                .map(paymentOptionEntityMapper::toPaymentOption)
                .collect(Collectors.toList());
        }
    }

}
