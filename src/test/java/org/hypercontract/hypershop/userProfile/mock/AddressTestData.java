package org.hypercontract.hypershop.userProfile.mock;

import com.github.javafaker.Faker;
import org.hypercontract.hypershop.userProfile.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressTestData {

    private static final int ADDRESS_COUNT = 5;

    private List<Address> addresses;
    private Address address;
    private Address anotherAddress;

    private  AddressTestData(MockAddressBuilder mockAddressBuilder) {
        this.addresses = mockAddressBuilder.buildMany()
            .limit(ADDRESS_COUNT)
            .collect(Collectors.toUnmodifiableList());

        this.address = this.addresses.get(0);
        this.anotherAddress = this.addresses.get(1);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Address getAddress() {
        return address;
    }

    public Address getAnotherAddress() {
        return anotherAddress;
    }

    private static AddressTestData instance;

    public static AddressTestData getInstance () {
        if (AddressTestData.instance == null) {
            AddressTestData.instance = new AddressTestData(
                new MockAddressBuilder(new Faker())
            );
        }
        return AddressTestData.instance;
    }
}
