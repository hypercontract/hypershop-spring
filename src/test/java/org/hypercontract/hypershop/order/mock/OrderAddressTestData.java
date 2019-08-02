package org.hypercontract.hypershop.order.mock;

import org.hypercontract.hypershop.order.OrderAddress;
import org.hypercontract.hypershop.userProfile.mock.AddressTestData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderAddressTestData {

    private static final int ORDER_ADDRESS_COUNT = 2;

    private final List<OrderAddress> orderAddresses;
    private final OrderAddress orderAddress;
    private final OrderAddress anotherOrderAddress;

    private OrderAddressTestData() {
        this.orderAddresses = AddressTestData.getInstance().getAddresses().stream()
            .limit(ORDER_ADDRESS_COUNT)
            .map(address -> OrderAddress.builder()
                .name(address.getName())
                .street(address.getCity())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .build())
            .collect(Collectors.toUnmodifiableList());

        this.orderAddress = this.orderAddresses.get(0);
        this.anotherOrderAddress = this.orderAddresses.get(1);
    }

    public List<OrderAddress> getOrderAddresses() {
        return orderAddresses;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }
    public OrderAddress getAnotherOrderAddress() {
        return anotherOrderAddress;
    }

    private static OrderAddressTestData instance;

    public static OrderAddressTestData getInstance () {
        if (OrderAddressTestData.instance == null) {
            OrderAddressTestData.instance = new OrderAddressTestData();
        }
        return OrderAddressTestData.instance;
    }
}
