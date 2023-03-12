package org.shock.calculator.operator;

public class SubtractionOperator implements NewArithmeticOperator {

    @Override
    public boolean supports(final String operator) {
        return "-".equals(operator);
    }

    @Override
    public double operate(final double operand1, final double operand2) {
        return operand1 - operand2;
    }
}
