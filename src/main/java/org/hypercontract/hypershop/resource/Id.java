package org.hypercontract.hypershop.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@JsonDeserialize(using = IdDeserializer.class)
public class Id<T> {

    private final String id;

    public Id() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return this.id;
    }

}
