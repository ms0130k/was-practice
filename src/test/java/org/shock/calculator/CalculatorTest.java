package org.shock.calculator;

import org.shock.calculator.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 간단한 사칙연산을 할 수 있다.
 * 양수로만 계산할 수 있다.
 * 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 * MVC패턴(Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {
    @ParameterizedTest
    @MethodSource("사칙연산_인자와_결과")
    void 사칙연산(final double operand1, final double operand2, final String operator, final double expected) {
        double result = Calculator.calculate(operand1, operand2, operator);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> 사칙연산_인자와_결과() {
        return Stream.of(
                arguments(1, 2, "+", 3),
                arguments(1, 2, "-", -1),
                arguments(1, 2, "*", 2),
                arguments(1, 2, "/", 0.5)
        );
    }

    @Test
    void 사칙연산_외_입력시_예외발생() {
        assertThatCode(() -> Calculator.calculate(1, 2, "!")).isInstanceOf(IllegalArgumentException.class).hasMessage(Calculator.IS_NOT_ARITHMETIC_OPERATOR);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1})
    void 양수만_입력가능(double operand) {
        assertThatCode(() -> Calculator.calculate(operand, operand, "+")).isInstanceOf(IllegalArgumentException.class).hasMessage(Calculator.IS_NOT_POSITIVE);
    }
}




