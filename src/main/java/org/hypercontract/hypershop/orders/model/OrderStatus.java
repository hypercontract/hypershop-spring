package org.hypercontract.hypershop.orders.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

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

}
