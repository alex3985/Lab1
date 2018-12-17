package sample.lab5.deprecatedShitDontTouch;

import java.util.Scanner;
import java.util.Stack;

public strictfp class FunctionOneVariable{
    private String expression;

/**
 * The expression should be in either x or y variable, otherwise the
 * function will show UndefinedVariableException.
 *
 * @param exp the expression to evaluate
 */
public FunctionOneVariable(String exp) {
    expression = exp.trim();
    expression = indentateOpertors(expression);
    expression = removeUnnecessarySpaces(expression);
    if (!isBalanced(expression)) {
    parenthesize();
    } else {
    System.out.println("Balanced parenthesized expression : " + expression);
    }
    }

public double evaluate(double x) throws UndefinedVariableException {
    String xp = expression.replace("x", x + "").trim();
    System.out.println("substituted value expression : " + xp);
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

private String indentateOpertors(String exp) {
    return getOperation(exp);
}

    static String getOperation(String exp) {
        exp = exp.replace("+", " + ");
        exp = exp.replace("-", " - ");
        exp = exp.replace("*", " * ");
        exp = exp.replace("/", " / ");
        exp = exp.replace("^", " ^ ");
        exp = exp.replace("%", " % ");
        exp = exp.replace(",", " , ");
        exp = exp.replace(")", " ) ");
        exp = exp.replace("pow", " pow ");
        exp = exp.replace("log", " log ");
        exp = exp.replace("exp", " exp ");
        exp = exp.replace("sqrt", " sqrt ");
        exp = exp.replace("sin", " sin ");
        exp = exp.replace("tan", " tan ");
        exp = exp.replace("cos", " cos ");
        return exp;
    }

    private String removeUnnecessarySpaces(String exp) {
        return getRemovedSpaces(exp);
    }

    static String getRemovedSpaces(String exp) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < exp.length() - 1) {
        if (exp.charAt(i) == ' ') {
        while (exp.charAt(i + 1) == ' ') {
        i++;
        }
        sb.append(' ');
        } else {
        sb.append(exp.charAt(i));
        }
        i++;
        }
        if (exp.charAt(exp.length() - 1) != ' ') {
        sb.append(exp.charAt(exp.length() - 1));
        }

        return sb.toString();
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

    static boolean isOperate(String ex) {
        switch (ex) {
        case "+":
        return true;
        case "-":
        return true;
        case "*":
        return true;
        case "/":
        return true;
        case "pow":
        return true;
        case "exp":
        return true;
        case "log":
        return true;
        case "sin":
        return true;
        case "cos":
        return true;
        case "tan":
        return true;
    default:
        return false;
        }
    }

    public static class UndefinedVariableException extends Exception {

    public UndefinedVariableException() {
        super("The variable must be x!!");
    }
}
    public static void main(String args[]) throws UndefinedVariableException {
        System.out.println("answer : " + new FunctionOneVariable("sqrt x").evaluate(9));
        //System.out.println("answer : " + new FunctionOneVariable("( ( 1 + sqrt ( pow ( x  x ) ) ) / 2.0 )").evaluate(3));

    }
}