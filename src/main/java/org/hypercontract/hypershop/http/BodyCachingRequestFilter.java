package org.hypercontract.hypershop.http;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class BodyCachingRequestFilter implements Filter {

    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain chain
    ) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) servletRequest;
        var wrappedRequest = new BodyCachingRequestWrapper(httpServletRequest);

        chain.doFilter(wrappedRequest, servletResponse);
    }

}