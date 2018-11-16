package DataHandling;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Calculator implements CalculatorInterface {

    @Override
    public String clearInputString(String input) {
        return input.replaceAll("[^\\d\\+\\-\\*\\/]*", "");
    }

    @Override
    public String getPolskaRecord(String input) {
        //StringBuilder stringBuilderOperations = new StringBuilder();
        LinkedList<Character> linkedListOperations = new LinkedList<>();
        StringBuilder stringBuilderResult = new StringBuilder();
        char previousOperation;

        for (char currentSymbol : input.toCharArray()) {
            if (isOperation(currentSymbol)) {
                if (linkedListOperations.isEmpty()) {
                    stringBuilderResult.append(" ");
                    linkedListOperations.add(currentSymbol);
                } else {
                    previousOperation = linkedListOperations.pollLast();
                    if (getOperationPriority(currentSymbol) <= getOperationPriority(previousOperation)) {
                        stringBuilderResult.append(" ");
                        linkedListOperations.addLast(currentSymbol);
                        stringBuilderResult.append(previousOperation);
                        stringBuilderResult.append(" ");
                    } else {
                        stringBuilderResult.append(" ");
                        linkedListOperations.addFirst(currentSymbol);
                        linkedListOperations.addLast(previousOperation);
                    }
                }
            } else {
                stringBuilderResult.append(currentSymbol);
            }
        }
        for (char operation : linkedListOperations) {
            stringBuilderResult.append(" ");
            stringBuilderResult.append(operation);
        }

        return stringBuilderResult.toString();
    }

    @Override
    public double calculateResult(String input) throws Exception {
        double firstNum = 0;
        double secondNum = 0;
        String tempString;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer stringTokenizer = new StringTokenizer(input);

        while (stringTokenizer.hasMoreTokens()) {
            try {
                tempString = stringTokenizer.nextToken().trim();
                if (tempString.length() == 1 && isOperation(tempString)) {
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(calculateOperation(firstNum, secondNum, tempString.charAt(0)));
                } else {
                    firstNum = Double.parseDouble(tempString);
                    stack.push(firstNum);
                }
            } catch (Exception e) {
                throw new Exception("Недопустимый символ в выражении");
            }
        }
        return stack.pop();
    }

    private double calculateOperation(double firstNum, double secondNum, char sign) throws Exception {
        switch (sign) {
            case '+':
                firstNum += secondNum;
                break;
            case '-':
                firstNum -= secondNum;
                break;
            case '/':
                firstNum /= secondNum;
                break;
            case '*':
                firstNum *= secondNum;
                break;
            default:
                throw new Exception("Недопустимая операция");
        }

        return firstNum;
    }

    private boolean isOperation(char sym) {
        switch (sym) {
            case '-':
            case '+':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    private boolean isOperation(String sym) {
        if (sym.length() == 1) {
            char c = sym.charAt(0);
            switch (c) {
                case '-':
                case '+':
                case '*':
                case '/':
                    return true;
            }
        }
        return false;
    }

    private byte getOperationPriority(char operation) {
        switch (operation) {
            case '*':
            case '/':
                return 2;
        }
        return 1;
    }
}

