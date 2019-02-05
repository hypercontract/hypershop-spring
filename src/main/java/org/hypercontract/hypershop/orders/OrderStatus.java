package org.hypercontract.hypershop.orders;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    CANCELLED("Cancelled"),
    DELIVERED("Delivered"),
    IN_TRANSIT("InTransit"),
    PAYMENT_DUE("PaymentDue"),
    PICKUP_AVAILABLE("PickupAvailable"),
    PROBLEM("Problem"),
    PROCESSING("Processing"),
    RETURNED("Returned");

    private final String status;

    OrderStatus(final String status) {
        this.status = status;
    }

    @Override
    @JsonValue
    public String toString() {
        return status;
    }

    public boolean is(String value) {
        return toString().equals(value);
    }

}
