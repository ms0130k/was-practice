package org.shock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
public class StringTest {
    private String text1;
    private String text2;

    @BeforeEach
    void setup() {
        text1 = "aaa:bbb:ccc";
        text2 = "aaa:bbb:ccc::";
    }

    @ParameterizedTest
    @MethodSource("textAndLength")
    void split() {
        String[] tokens = text1.split(":");
        assertThat(tokens).isNotNull();
        assertThat(tokens.length).isEqualTo(3);
    }

    static Stream<Arguments> textAndLength() {
        return Stream.of(
                arguments("aaa:bbb:ccc", 3),
                arguments("", 0)
        );
    }

    @Test
    void split_limit() {
        String[] tokens = text2.split(":", 0);
        for (String token : tokens) {
            log.info(token);
        }
    }
}
