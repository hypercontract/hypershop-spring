package org.hypercontract.hypershop.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class RequestBodyRequestCondition implements RequestCondition<RequestBodyRequestCondition> {

    private final Class[] targetClasses;
    private final String condition;

    private ObjectMapper mapper = new ObjectMapper();

    RequestBodyRequestCondition(Class[] targetClasses, String condition) {
        this.targetClasses = targetClasses;
        this.condition = condition;
    }

    @Override
    public RequestBodyRequestCondition combine(RequestBodyRequestCondition other) {
        return new RequestBodyRequestCondition(
            ArrayUtils.addAll(this.targetClasses, other.targetClasses),
            String.format("(%s) and (%s)", this.condition, other.condition)
        );
    }

    @Override
    public RequestBodyRequestCondition getMatchingCondition(HttpServletRequest request) {
        if (isPatch(request) == false) {
            return null;
        }

        return bodyMatches(request, this.targetClasses, this.condition) ? this : null;
    }

    @Override
    public int compareTo(RequestBodyRequestCondition other, HttpServletRequest request) {
        var targetClassNames = toClassNames(this.targetClasses);
        var otherTargetClassNames = toClassNames(other.targetClasses);

        return Arrays.compare(targetClassNames, otherTargetClassNames);
    }

    private boolean isPatch(HttpServletRequest request) {
        return "PATCH".equalsIgnoreCase(request.getMethod());
    }

    private boolean bodyMatches(HttpServletRequest request, Class[] targetClasses, String condition) {
        var requestBody = parseRequestBody(request, targetClasses);

        if (requestBody.isEmpty()) {
            return false;
        }

        return requestBodyMatchesCondition(requestBody.get(), condition);
    }

    private boolean requestBodyMatchesCondition(Object requestBody, String condition) {
        if (condition.isEmpty()) {
            return true;
        }

        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(condition);
        EvaluationContext context = new StandardEvaluationContext(requestBody);
        return (boolean) expression.getValue(context);
    }

    private Optional<Object> parseRequestBody(HttpServletRequest request, Class[] targetClasses) {
        String body;

        try {
            body = request
                .getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            return Optional.empty();
        }

        return parseRequestBody(body, targetClasses);
    }

    private Optional<Object> parseRequestBody(String body, Class[] classes) {
        return Arrays.stream(classes)
            .map(cls -> parseRequestBody(body, cls))
            .filter(Objects::nonNull)
            .findAny();
    }

    private Object parseRequestBody(String body, Class cls) {
        try {
            return mapper.readValue(body, cls);
        } catch (IOException e) {
            return null;
        }
    }

    private String[] toClassNames(Class[] classes) {
        var classNames = Arrays.stream(this.targetClasses)
            .map(Class::getName)
            .toArray(String[]::new);
        Arrays.sort(classNames);
        return classNames;
    }

}
