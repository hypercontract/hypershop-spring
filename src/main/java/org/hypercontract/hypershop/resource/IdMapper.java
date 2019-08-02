package org.hypercontract.hypershop.resource;

public class IdMapper {

    public static <T> String toString(Id<T> id) {
        return id.toString();
    }

    public static <T> Id<T> toId(String id) {
        return new Id<>(id);
    }

}

