package org.hypercontract.hypershop.userProfile;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hypercontract.hypershop.userProfile.jpa.AddressEntityMapper;
import org.hypercontract.hypershop.userProfile.jpa.AddressRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AddressMockFactory {

    private final Faker faker = new Faker();

    private final AddressRepository addressRepository;

    private final AddressEntityMapper addressEntityMapper = Mappers.getMapper(AddressEntityMapper.class);

    public List<Address> createAddresses(int addressCount) {
        return Stream.generate(this::createAddress)
            .limit(addressCount)
            .peek(address -> addressRepository.save(
                addressEntityMapper.toEntity(address)
            ))
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
