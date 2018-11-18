package org.hypercontract.hypershop.resource;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.lang.reflect.InvocationTargetException;

public class ResourceIdConverterFactory implements ConverterFactory<String, ResourceId> {

    @Override
    public <T extends ResourceId> Converter<String, T> getConverter(Class<T> targetType) {
        return new ResourceIdConverter(targetType);
    }

    @AllArgsConstructor
    private static class ResourceIdConverter<T extends ResourceId> implements Converter<String, T> {

        private final Class<T> resourceIdClass;

        @Override
        public T convert(String id) {
            try {
                return resourceIdClass.getDeclaredConstructor(String.class).newInstance(id);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
