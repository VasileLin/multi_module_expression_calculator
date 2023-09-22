package com.vasile.linga;

public class CalculatorExceptions{

    public static class UnbalancedParenthesisException extends Exception{
        public UnbalancedParenthesisException(String message) {
            super(message);
        }
    }

    public static class InvalidCharacterException extends Exception{
        public InvalidCharacterException(String message) {
            super(message);
        }
    }

    public static class InvalidOperatorException extends Exception{
        public InvalidOperatorException(String message) {
            super(message);
        }
    }
}
