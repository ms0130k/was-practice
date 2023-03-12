package org.shock.was;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
class QueryStringsTest {

    @Test
    void set() {
        List<QueryString> queryStrings = new QueryStrings("operand1=11&operand2=55&operator=+").queryStrings;
        assertThat(queryStrings.get(0).getKey()).isEqualTo("operand1");
        assertThat(queryStrings.get(0).getValue()).isEqualTo("11");
        assertThat(queryStrings.get(1).getKey()).isEqualTo("operand2");
        assertThat(queryStrings.get(1).getValue()).isEqualTo("55");
        assertThat(queryStrings.get(2).getKey()).isEqualTo("operator");
        assertThat(queryStrings.get(2).getValue()).isEqualTo("+");
    }
}