package sample.lab5.deprecatedShitDontTouch;

import static sample.lab5.deprecatedShitDontTouch.FunctionOneVariable.getOperation;
import static sample.lab5.deprecatedShitDontTouch.FunctionOneVariable.getRemovedSpaces;
import static sample.lab5.deprecatedShitDontTouch.FunctionOneVariable.isOperate;

import java.util.Scanner;
import java.util.Stack;

public strictfp class FunctionTwoVariable {
    String expression;

    /**
     * The expression should be in either x or y variable, otherwise the
     * function will show UndefinedVariableException.
     *
     * @param exp the expression to evaluate
     */
    public FunctionTwoVariable(String exp) {
        expression = exp.trim();
        expression = indentateOpertors(expression);
        expression = removeUnnecessarySpaces(expression);
        if (!isBalanced(expression)) {
            parenthesize();
        } else {
      //      System.out.println("Balanced parenthesized expression : " + expression);
        }
    }

    public double evaluate(double x, double y) throws UndefinedVariableException {
        String xp = expression.replace("x", x + "").replace("y", y + "").trim();
      //  System.out.println("substituted value expression : " + xp);
        Scanner inputStream = new Scanner(xp);
        Stack<String> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();
        while (inputStream.hasNext()) {
            String currentToken = inputStream.next();
            //System.out.println(currentToken);
            switch (currentToken) {
                case "(":
                    break;
                case "+":
                    operators.push(currentToken);
                    break;
                case "-":
                    operators.push(currentToken);
                    break;
                case "*":
                    operators.push(currentToken);
                    break;
                case "/":
                    operators.push(currentToken);
                    break;
                case "sqrt":
                    operators.push(currentToken);
                    break;
                case "sin":
                    operators.push(currentToken);
                    break;
                case "cos":
                    operators.push(currentToken);
                    break;
                case "tan":
                    operators.push(currentToken);
                    break;
                case "cot":
                    operators.push(currentToken);
                    break;
                case "exp":
                    operators.push(currentToken);
                    break;
                case "pow":
                    operators.push(currentToken);
                    break;
                case "log":
                    operators.push(currentToken);
                    break;
                case ")":
                    String op = operators.pop();
                    double v = operands.pop();
                    switch (op) {
                        case "+":
                            v = operands.pop() + v;
                            break;
                        case "-":
                            v = operands.pop() - v;
                            break;
                        case "*":
                            v = operands.pop() * v;
                            break;
                        case "/":
                            v = operands.pop() / v;
                            break;
                        case "sqrt":
                            v = Math.sqrt(v);
                            break;
                        case "sin":
                            v = Math.sin(v);
                            break;
                        case "cos":
                            v = Math.cos(v);
                            break;
                        case "tan":
                            v = Math.tan(v);
                            break;
                        case "exp":
                            v = Math.exp(v);
                            break;
                        case "log":
                            v = Math.sin(v);
                            break;
                        case "pow":
                            v = Math.pow(operands.pop(), v);
                            break;
                    }
                    operands.push(v);
                    break;
                default:
                    try {
                        operands.push(Double.parseDouble(currentToken));
                    } catch (NumberFormatException ex) {
                        throw new UndefinedVariableException();
                    }
                    break;
            }
        }
        //System.out.println(operands);
        return (double) operands.pop();
    }

    public FunctionOneVariable toFunctionInX(double y) {
        return new FunctionOneVariable(expression.replace("y ", y + " ").replace(" y", " " + y));
    }

    public FunctionOneVariable toFunctionInY(double x) {
        return new FunctionOneVariable(expression.replace("x ", x + " ").replace(" x", " " + x));
    }

    private String indentateOpertors(String exp) {
        return getOperation(exp);
    }

    private String removeUnnecessarySpaces(String exp) {
        return getRemovedSpaces(exp);
    }

    private void parenthesize() {
        Scanner sc = new Scanner(expression);
        StringBuilder sb = new StringBuilder("( ");
        boolean isOperator = false;
        while (sc.hasNext()) {
            String next = sc.next();
            if (!isOperator(next) && !isOperator) {
                sb = sb.append("( ");
            }
            sb = sb.append(next).append(" ");
            if (!isOperator(next) && isOperator) {
                isOperator = false;
                sb = sb.append(") ");
            } else if (isOperator(next)) {
                isOperator = true;
            }
        }
        if (!isBalanced(sb.toString())) {
            sb = sb.append(")");
        }
        expression = sb.toString();
        System.out.println("Balanced parenthesized expression : " + expression);
    }

    private boolean isBalanced(String x) {
        int start = 0, end = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '(') {
                start++;
            } else if (x.charAt(i) == ')') {
                end++;
            }
        }
        return start == end && start != 0;
    }

    private boolean isOperator(String ex) {
        return isOperate(ex);
    }

    public static class UndefinedVariableException extends Exception {

        public UndefinedVariableException() {
            super("The variable must be either x or y !!");
        }
    }
}
