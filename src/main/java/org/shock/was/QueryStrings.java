package org.shock.was;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {
    public static final String ILLEGAL_QUERYSTRING_FORMAT = "잘못된 QueryString 포맷을 가진 문자열입니다.";
    public final List<QueryString> queryStrings = new ArrayList<>();

    public QueryStrings(String queryStringsLine) {
        Arrays.stream(queryStringsLine.split("&"))
                .forEach(queryString -> {
                    String[] tokens = queryString.split("=");
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException(ILLEGAL_QUERYSTRING_FORMAT);
                    }
                    queryStrings.add(new QueryString(tokens[0], tokens[1]));
                });
    }

    public String getValue(final String key) {
        return queryStrings.stream()
                .filter(queryString -> key.equals(queryString.getKey()))
                .map(QueryString::getValue)
                .findFirst()
                .orElse(null);
    }
}
