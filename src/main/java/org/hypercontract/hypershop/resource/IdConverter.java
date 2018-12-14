package org.hypercontract.hypershop.resource;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class IdConverter implements Converter<String, Id> {

    @Override
    public Id convert(String id) {
        return IdMapper.toId(id);
    }

}
