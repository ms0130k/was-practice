package org.shock.was;

import lombok.Getter;

import java.util.Objects;

@Getter
public class RequestLine {
    private final String method;
    private final String urlPath;
    private String queryStringLine;

    public RequestLine(final String method, final String urlPath, final String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStringLine = queryString;
    }

    public RequestLine(final String requestLine) {
        String[] requestLineTokens = requestLine.split(" ");
        this.method = requestLineTokens[0];
        String urlPathAndQueryString = requestLineTokens[1];
        String[] urlPathAndQueryStringTokens = urlPathAndQueryString.split("\\?");
        this.urlPath = urlPathAndQueryStringTokens[0];

        if (queryStringExists(urlPathAndQueryStringTokens)) {
            this.queryStringLine = urlPathAndQueryStringTokens[1];
        }
    }

    private boolean queryStringExists(final String[] urlPathAndQueryString) {
        return urlPathAndQueryString.length == 2;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStringLine, that.queryStringLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStringLine);
    }
}
