package org.hypercontract.hypershop.rest;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public final class ResourceId {

    private final String id;

    public ResourceId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return this.id;
    }

}
