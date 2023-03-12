package org.shock.calculator;

import org.shock.calculator.operator.*;

import java.util.Arrays;
import java.util.List;

public class Calculator {

    public static final String IS_NOT_ARITHMETIC_OPERATOR = "올바른 사칙연산이 아닙니다.";
    public static final String IS_NOT_POSITIVE = "양수가 아닙니다.";
    private static final List<NewArithmeticOperator> newArithmeticOperators = Arrays.asList(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());

    public static double calculate(final double operand1, final double operand2, final String operator) {
        if (isNotPositive(operand1) || isNotPositive(operand2)) {
            throw new IllegalArgumentException(IS_NOT_POSITIVE);
        }
        return newArithmeticOperators.stream()
                .filter(v -> v.supports(operator))
                .map(v -> v.operate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(IS_NOT_ARITHMETIC_OPERATOR));
        //        return Arrays.stream(ArithmeticOperator.values())
//                .filter(v -> v.getOperator().equals(operator))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(IS_NOT_ARITHMETIC_OPERATOR))
//                .calculate(operand1, operand2);
//        return ArithmeticOperator.calculate(operand1, operand2, operator);
    }

    private static boolean isNotPositive(final double number) {
        return number <= 0;
    }
}
