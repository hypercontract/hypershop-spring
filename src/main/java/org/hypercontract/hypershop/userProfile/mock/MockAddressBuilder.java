package org.hypercontract.hypershop.userProfile.mock;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.userProfile.Address;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
class MockAddressBuilder {

    private final Faker faker;

    public Stream<Address> buildMany() {
        return Stream.generate(this::buildAddress);
    }

    private Address buildAddress() {
        return Address.builder()
            .name(faker.name().name())
            .street(faker.address().streetAddress())
            .zipCode(faker.address().zipCode())
            .city(faker.address().city())
            .country(faker.address().country())
            .build();
    }

}
