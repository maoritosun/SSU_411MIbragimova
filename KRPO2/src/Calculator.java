import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Toshiba on 22.01.2016.
 */

public class Calculator {

    static void noHigherPriority(String t, Stack temp, Stack postfix) {
        String t1 = temp.peek().toString();
        if(t1.equals("(")) {
            temp.push(t);
        }
        else {
            postfix.push(t1);
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
        String t;
        double operand1, operand2 ,result1 = 0.0, result2;
        Stack<Double> tempStack = new Stack<Double>();
        while(!st.isEmpty()){
            t = st.pop();
            if(t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/") || t.equals("^")){
                operand1 = tempStack.pop();
                operand2 = tempStack.pop();
                switch (t) {
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
                result2 = Double.valueOf(t);
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
                        } else if (peekTempStack.equals("+")) { // равный
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
        Stack st;
        Scanner sc = new Scanner(System.in);
        String[] alphabet = {"+", "-", "*","/","^","(",")","1","2","3","4","5","6","7","8","9","0"};
        int marker1;
        String[] exp = new String[0];
        loop: do {
            System.out.println("Input expression: ");
            String expt = sc.nextLine();

            if(expt.trim().equals("close")) {
                break;
            }

            exp = expt.split(" ");
            for (int i = 0; i < exp.length; i++) {
                marker1 = 0;
                for (int j = 0; j < alphabet.length; j++) {
                    if (exp[i].equals(alphabet[j])) {
                        marker1 = 1;
                    }
                }
                if (marker1 == 0) {
                    System.out.println("Wrong expression!");
                    continue loop;
                }
            }

            st = postfix(exp);
            st = reverseStack(st);
            System.out.println("Result: ");
            double res;
            res = calculate(st);
            System.out.println(res);
        } while(true);
    }
}
