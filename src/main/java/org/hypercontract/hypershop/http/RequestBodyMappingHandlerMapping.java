package org.hypercontract.hypershop.http;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class RequestBodyMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        RequestBodyMapping methodAnnotation = AnnotationUtils.findAnnotation(
            method,
            RequestBodyMapping.class
        );
        return createCondition(methodAnnotation);
    }

    private RequestCondition<?> createCondition(RequestBodyMapping accessMapping) {
        if (accessMapping == null) {
            return null;
        }

        return new RequestBodyRequestCondition(accessMapping.value(), accessMapping.condition());
    }

}
