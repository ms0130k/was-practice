package org.shock.was;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final RequestLine requestLine;

    public HttpRequest(final BufferedReader br) {
        try {
            this.requestLine = new RequestLine(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isGet() {
        return "GET".equals(requestLine.getMethod());
    }

    public boolean matchPath(final String urlPath) {
        return urlPath.equals(requestLine.getUrlPath());
    }

    public QueryStrings getQeuryStrings() {
        return new QueryStrings(requestLine.getQueryStringLine());
    }
}
