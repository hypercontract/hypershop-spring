package org.hypercontract.hypershop.http;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.stream.Collectors;

class BodyCachingRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public BodyCachingRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        this.body = request
            .getReader()
            .lines()
            .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public BufferedReader getReader() {
        Reader inputString = new StringReader(this.body);
        return new BufferedReader(inputString);
    }

    @Override
    public ServletInputStream getInputStream () {
        var inputStream = new ByteArrayInputStream(body.getBytes());

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return inputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                throw new RuntimeException("Not implemented");
            }

            public int read () throws IOException {
                return inputStream.read();
            }
        };
    }
}
