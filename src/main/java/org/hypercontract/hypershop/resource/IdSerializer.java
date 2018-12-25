package org.hypercontract.hypershop.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class IdSerializer extends StdSerializer<Id> {

    private IdConverter converter = new IdConverter();

    public IdSerializer() {
        this(null);
    }

    public IdSerializer(Class<Id> t) {
        super(t);
    }

    @Override
    public void serialize(Id value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(IdMapper.toString(value));
    }
}
