package com.vasile.linga;

import java.util.Stack;

public class Algorithm {
    public static Stack<Character> operatori = new Stack<>();
    public static Stack<Integer> operanzi = new Stack<>();

    private static int ComparePrecedence(char operator1, char operator2) {
        if (operator1 == '+' || operator1 == '-')
            if (operator2 == '+' || operator2 == '-')
                return 0;
            else
                return -1;
        else if (operator2 == '+' || operator2 == '-')
            return 1;
        else
            return 0;
    }

    public static int DoCalculation(String expression) throws Exception{
        char previous = 0;
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '(':
                    operatori.push(expression.charAt(i));
                    break;
                case '+':
                case '-':
                    if (previous == '+' || previous == '-' || previous == '*' || previous == '/') {
                        throw new CalculatorExceptions.InvalidOperatorException("Expresie invalida(incearca a+(-b))");
                    }
                    if (previous == 0 || previous == '(')
                        operanzi.push(0);
                case '*':
                case '/':
                    while (!operatori.isEmpty() && operatori.peek() != '(')
                        if (ComparePrecedence(operatori.peek(), expression.charAt(i)) >= 0) {
                            int op2 = operanzi.pop();
                            int result =Utilites.Calculate(operanzi.pop(), op2, operatori.pop());
                            operanzi.push(result);
                        } else
                            break;
                    operatori.push(expression.charAt(i));
                    break;
                case ')':
                    //Efectuam operatiile din paranteza
                    while (!operatori.isEmpty() && operatori.peek() != '(') {
                        int op2 = operanzi.pop();
                        operanzi.push(Utilites.Calculate(operanzi.pop(), op2, operatori.pop()));
                    }
                    if (operatori.empty())
                        throw new CalculatorExceptions.UnbalancedParenthesisException("O paranteza deschisa a fost inchisa de mai multe ori");
//                        Stergem '(' dupa efectuarea calculelor
                    operatori.pop();
                    break;
                case ' ' :
                case '\t':
                    break;
                case '.' :
                    if (Character.isDigit(previous))
                        throw new CalculatorExceptions.InvalidCharacterException("Aplicatia nu suporta valori zecimale,only integers!");
                default:
                    //Extragem cifra,numarul
                    if (Character.isDigit(expression.charAt(i))) {
                        int temp = Utilites.GetOperand(expression, i);
                        //Extragem numarul cu mai multe cifre apoi mutam pozitia i la sfarsitul numarului
                        while (i < expression.length() && Character.isDigit(expression.charAt(i)))
                            i++;
                        i--;
                        operanzi.push(temp);
                    } else
                        throw new CalculatorExceptions.InvalidCharacterException("nu introdu litere sau caractere,doar +,-,*,/");
            }
            //Tinem evidenta caracterului anterior
            if (!Character.isWhitespace(expression.charAt(i)))
                previous = expression.charAt(i);
        }
        //Efectuam operatiile ramase in stack-ul operatori cu operanzii ramasi in stack-ul operanzi
        while (!operatori.isEmpty()) {
            int op2 = operanzi.pop();
            operanzi.push(Utilites.Calculate(operanzi.pop(), op2, operatori.pop()));
        }
        return operanzi.pop();
    }

}
