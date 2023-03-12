package org.shock.was;

import lombok.Getter;

@Getter
public class QueryString {
    private final String key;
    private final String value;

    public QueryString(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
