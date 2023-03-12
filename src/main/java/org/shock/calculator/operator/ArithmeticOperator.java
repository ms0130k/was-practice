package org.shock.calculator.operator;

public enum ArithmeticOperator {
    ADDITION("+") {
        @Override
        public double calculate(final double operand1, final double operand2) {
            return operand1 + operand2;
        }
    }, SUBTRACTION("-") {
        @Override
        public double calculate(final double operand1, final double operand2) {
            return operand1 - operand2;
        }
    }, MULTIPLICATION("*") {
        @Override
        public double calculate(final double operand1, final double operand2) {
            return operand1 * operand2;
        }
    }, DIVISION("/") {
        @Override
        public double calculate(final double operand1, final double operand2) {
            return operand1 / operand2;
        }
    };

    private final String operator;

    ArithmeticOperator(final String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public abstract double calculate(double operand1, double operand2);

}
