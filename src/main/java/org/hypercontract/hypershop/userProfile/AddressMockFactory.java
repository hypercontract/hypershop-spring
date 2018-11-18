package org.hypercontract.hypershop.userProfile;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AddressMockFactory {

    private final Faker faker = new Faker();

    public List<Address> createAddresses(int addressCount) {
        return Stream.generate(this::createAddress)
            .limit(addressCount)
            .collect(Collectors.toList());
    }

    private Address createAddress() {
        return Address.builder()
            .name(faker.name().name())
            .street(faker.address().streetAddress())
            .zipCode(faker.address().zipCode())
            .city(faker.address().city())
            .country(faker.address().country())
            .build();
    }

}
