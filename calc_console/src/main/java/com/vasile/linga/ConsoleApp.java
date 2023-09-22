package com.vasile.linga;


import java.util.EmptyStackException;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) throws Exception {
        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdu expresia: ");
            String expression = scanner.nextLine();
            try {
                if (expression.equals("stop") || expression.equals("exit")){
                    flag = false;
                }else {
                    System.out.println(Algorithm.DoCalculation(expression));
                }
            } catch (EmptyStackException e){
                System.out.println("Expresie invalida,reintrodu expresia");
                Utilites.ClearStacks(Algorithm.operatori,Algorithm.operanzi);

            } catch (CalculatorExceptions.UnbalancedParenthesisException e){
                System.out.println(e.getMessage());
                Utilites.ClearStacks(Algorithm.operatori,Algorithm.operanzi);

            } catch (CalculatorExceptions.InvalidCharacterException e){
                Utilites.ClearStacks(Algorithm.operatori,Algorithm.operanzi);

            } catch (CalculatorExceptions.InvalidOperatorException e){
                Utilites.ClearStacks(Algorithm.operatori,Algorithm.operanzi);
            }
        }

    }
}