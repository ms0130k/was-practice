package org.shock.was;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RequestLineTest {
    @Test
    void requestLine_생성() {
        RequestLine requestLine = new RequestLine("GET /calculate?operand1=11&operand2=55&operator=+ HTTP/1.1");
        assertThat(requestLine).isEqualTo(new RequestLine("GET", "/calculate", "operand1=11&operand2=55&operator=+"));
    }
}