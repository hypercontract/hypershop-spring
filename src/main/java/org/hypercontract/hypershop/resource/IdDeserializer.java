package org.hypercontract.hypershop.resource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class IdDeserializer extends StdDeserializer<Id> {

    public IdDeserializer() {
        this(null);
    }

    public IdDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Id deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        var converter = new IdConverter();
        return converter.convert(parser.getText());
    }

}
