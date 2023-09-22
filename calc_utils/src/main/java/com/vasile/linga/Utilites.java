package com.vasile.linga;

import java.util.Scanner;
import java.util.Stack;

public class Utilites {

    public static int GetOperand(String exp, int i) {
        Scanner scanner = new Scanner(exp.substring(i));
        scanner.useDelimiter("[^0-9]");
        return scanner.nextInt();
    }

    public static void ClearStacks(Stack<Character> operatori,Stack<Integer> operanzi){
        operatori.clear();
        operanzi.clear();
    }

    public static int Calculate(int operand1, int operand2, char operator) throws Exception {
        return switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> operand1 / operand2;
            default -> throw new CalculatorExceptions.UnbalancedParenthesisException("Paranteza nu a fost inchisa");
        };
    }
}
