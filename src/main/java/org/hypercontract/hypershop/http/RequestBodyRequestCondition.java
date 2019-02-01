package org.hypercontract.hypershop.http;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestBodyRequestCondition implements RequestCondition<RequestBodyRequestCondition> {

    private final String[] value;

    public RequestBodyRequestCondition(String[] value) {
        this.value = value;
    }

    @Override
    public RequestBodyRequestCondition combine(RequestBodyRequestCondition other) {
        return new RequestBodyRequestCondition(
            ArrayUtils.addAll(this.value, other.value)
        );
    }

    @Override
    public RequestBodyRequestCondition getMatchingCondition(HttpServletRequest request) {
        if (isPatch(request) == false) {
            return null;
        }

        return bodyMatches(request) ? this : null;
    }

    @Override
    public int compareTo(RequestBodyRequestCondition other, HttpServletRequest request) {
        var value = this.value.clone();
        Arrays.sort(value);
        var otherValue = other.value.clone();
        Arrays.sort(otherValue);

        return Arrays.compare(value, otherValue);
    }

    private boolean isPatch(HttpServletRequest request) {
        return "PATCH".equalsIgnoreCase(request.getMethod());
    }

    private boolean bodyMatches(HttpServletRequest request) {
        var body = readBody(request)
            .orElse("");

        return Arrays.stream(this.value)
            .filter(value -> body.contains(value))
            .findAny()
            .isPresent();
    }

    private Optional<String> readBody(HttpServletRequest request) {
        try {
            var body = request
                .getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

            return Optional.of(body);

        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
