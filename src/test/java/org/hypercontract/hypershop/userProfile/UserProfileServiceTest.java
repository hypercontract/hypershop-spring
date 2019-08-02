package org.hypercontract.hypershop.userProfile;

import org.hypercontract.hypershop.resource.Id;
import org.hypercontract.hypershop.userProfile.mock.AddressTestData;
import org.hypercontract.hypershop.userProfile.mock.PaymentOptionTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTest {

    @InjectMocks
    UserProfileService userProfileService;

    @Mock
    AddressRepository addressRepository;

    @Mock
    PaymentOptionRepository paymentOptionRepository;

    private final AddressTestData addressTestData = AddressTestData.getInstance();
    private final PaymentOptionTestData paymentOptionTestData = PaymentOptionTestData.getInstance();

    @Test
    public void getReturnsTheUserProfile() {
        var expectedAddresses = addressTestData.getAddresses();
        var expectedPaymentOptions = paymentOptionTestData.getPaymentOptions();
        when(addressRepository.findAll()).thenReturn(expectedAddresses.stream());
        when(paymentOptionRepository.findAll()).thenReturn(expectedPaymentOptions.stream());

        var userProfile = userProfileService.get();

        assertEquals(userProfile.getAddresses(), expectedAddresses);
        assertEquals(userProfile.getPaymentOptions(), expectedPaymentOptions);
    }

    @Test
    public void getAddressByIdReturnsAnAddress() {
        var expectedAddress = addressTestData.getAddress();
        var expectedId = expectedAddress.getId();
        when(addressRepository.findById(expectedId)).thenReturn(Optional.of(expectedAddress));

        var address = userProfileService.getAddressById(expectedId);

        assertSame(address, expectedAddress);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getAddressByIdThrowsIfIdIsInvalid() {
        when(addressRepository.findById(any(Id.class))).thenReturn(Optional.empty());

        userProfileService.getAddressById(new Id<>());
    }

    @Test
    public void getPaymentOptionByIdReturnsAPaymentOption() {
        var expectedPaymentOption = paymentOptionTestData.getPaymentOption();
        var expectedId = expectedPaymentOption.getId();
        when(paymentOptionRepository.findById(expectedId)).thenReturn(Optional.of(expectedPaymentOption));

        var paymentOption = userProfileService.getPaymentOptionById(expectedId);

        assertSame(paymentOption, expectedPaymentOption);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getPaymentOptionByIdThrowsIfIdIsInvalid() {
        when(paymentOptionRepository.findById(any(Id.class))).thenReturn(Optional.empty());

        userProfileService.getPaymentOptionById(new Id<>());
    }

}
