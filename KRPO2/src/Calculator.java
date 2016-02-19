import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Toshiba on 22.01.2016.
 */

public class Calculator {

    static void noHigherPriority(String t, Stack temp, Stack postfix) {
        String peekTemp = temp.peek().toString();
        if(peekTemp.equals("(")) {
            temp.push(t);
        }
        else {
            postfix.push(peekTemp);
            temp.pop();
            temp.push(t);
        }
    }

    static Stack reverseStack(Stack<String> st) {
        Stack<String> tempStack = new Stack<String>();
        String temp;
        while(!st.isEmpty()) {
            temp = st.pop();
            tempStack.push(temp);
        }
        return tempStack;
    }

    static double calculate(Stack<String> st) {
        double finalResult = 0;
        String symbol;
        double operand1, operand2 ,result1 = 0.0, result2;
        Stack<Double> tempStack = new Stack<Double>();
        while(!st.isEmpty()){
            symbol = st.pop();
            if(symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^")){
                operand1 = tempStack.pop();
                operand2 = tempStack.pop();
                switch (symbol) {
                    case "+": {
                        result1 = operand2 + operand1;
                        break;
                    }
                    case "-": {
                        result1 = operand2 - operand1;
                        break;
                    }
                    case "*": {
                        result1 = operand2 * operand1;
                        break;
                    }
                    case "/": {
                        result1 = operand2 / operand1;
                        break;
                    }
                    case "^": {
                        result1 = Math.pow(operand2,operand1);
                        break;
                    }
                }
                tempStack.push(result1);
            }
            else {//число
                result2 = Double.valueOf(symbol);
                tempStack.push(result2);
            }
        }
        finalResult = tempStack.pop();
        return finalResult;
    }

    static Stack postfix(String[] str){

        Stack postfix = new Stack();
        Stack tempStack = new Stack();
        String peekTempStack;
        for(int i = 0; i < str.length; i ++) {
            String valueSymbol;
            String symbolPriority;
            valueSymbol = str[i];
            symbolPriority = valueSymbol;
            if(tempStack.isEmpty()) {
                if(symbolPriority.equals("+") || symbolPriority.equals("-") || symbolPriority.equals("*") || symbolPriority.equals("/") || symbolPriority.equals("^") || symbolPriority.equals(")") || symbolPriority.equals("(")) {
                    tempStack.push(symbolPriority);
                }
                else {
                    postfix.push(symbolPriority);
                }
            }
            else {
                if (valueSymbol.equals("+") || valueSymbol.equals("-")) {
                    symbolPriority = "-";//низкий приоритет
                }
                if (valueSymbol.equals("*") || valueSymbol.equals("/") || valueSymbol.equals("^")) {
                    symbolPriority = "+";//высокий приоритет
                }
                switch (symbolPriority) {
                    case "+": {
                        peekTempStack = tempStack.peek().toString();
                        if (peekTempStack.equals("+") || peekTempStack.equals("-")) {
                            peekTempStack = "-";//низкий приоритет
                        }
                        else if (peekTempStack.equals("*") || peekTempStack.equals("/") || peekTempStack.equals("^")) {
                            peekTempStack = "+";//высокий приоритет
                        }
                        if (peekTempStack.equals("-")) {//в стеке - более низкий
                            tempStack.push(valueSymbol);
                        } else if (peekTempStack.equals("+")) { //равный
                            noHigherPriority(valueSymbol, tempStack, postfix);
                        }
                        else { // (
                            tempStack.push(valueSymbol);
                        }
                        break;
                    }
                    case "-": {
                        noHigherPriority(valueSymbol, tempStack, postfix);
                        break;
                    }
                    case "(": {
                        tempStack.push(valueSymbol);
                        break;
                    }
                    case ")": {
                        peekTempStack = tempStack.peek().toString();
                        while (peekTempStack.equals("+") || peekTempStack.equals("-") || peekTempStack.equals("*") || peekTempStack.equals("/") || peekTempStack.equals("^")) {
                            postfix.push(peekTempStack);
                            tempStack.pop();
                            peekTempStack = tempStack.peek().toString();
                        }
                        tempStack.pop();
                        break;
                    }
                    default:
                        postfix.push(valueSymbol);
                }
            }
        }
        while(!tempStack.isEmpty()) {
            peekTempStack = tempStack.peek().toString();
            postfix.push(peekTempStack);
            tempStack.pop();
        }
        return postfix;
    }

    public static void main(String[] args) {
        Stack<String> st;
        Scanner sc = new Scanner(System.in);
        String alphabet = "+-*/^.()1234567890";

        boolean flag;

        String[] exp;
        loop: do {
            System.out.println("Input expression: ");
            String expt = sc.nextLine();
            if(expt.trim().equals("close")) {
                break;
            }
            exp = expt.split(" ");

            for (String anExp : exp) {
                flag = false;

                if (alphabet.contains(anExp) && anExp.length() == 1) {// один разряд
                    flag = true;
                } else if (anExp.length() > 1) {// больше одного
                    for (char chr : anExp.toCharArray()) {
                        if (alphabet.contains(String.valueOf(chr))) {
                            flag = true;
                            break;
                        }
                    }
                }

                if (!flag) {
                    System.out.println("Wrong expression!");
                    continue loop;
                }
            }

            st = postfix(exp);
            Collections.reverse(st);
            System.out.println("Result: ");
            double res;
            res = calculate(st);
            System.out.println(res);
        } while(true);
    }
}
