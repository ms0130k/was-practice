package org.shock.calculator.operator;

public interface NewArithmeticOperator {
    public boolean supports(String operator);

    public double operate(double operand1, double operand2);
}
