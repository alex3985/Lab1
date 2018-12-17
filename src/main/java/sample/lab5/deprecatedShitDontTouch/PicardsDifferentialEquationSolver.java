package sample.lab5.deprecatedShitDontTouch;

import java.util.Arrays;

public strictfp class PicardsDifferentialEquationSolver extends Maths  {

    double x0, y0;
    FunctionTwoVariable func;
    int minimumNumberOfIntervals = 10;

    /**
     * The function takes initial guess of x and y as parameter along with the
     * function in two variable.
     *
     * @param x initial value x0
     * @param y initial value y0
     * @param f the function in two variable
     */
    public PicardsDifferentialEquationSolver(double x, double y, FunctionTwoVariable f) {
        x0 = x;
        y0 = y;
        func = f;
    }

    public double solveForX(double x, double h) throws FunctionOneVariable.UndefinedVariableException, AdaptiveQudrature.InsufficientDataPointsException, AdaptiveQudrature.UnequalSpacedDataPointsException {

        Double[] d = new Double[(int) ((x - x0) / h) + 1];
        double y = roundTo10Digits(y0);
        d[0] = roundTo10Digits(x0);
        for (int i = 1; i < d.length; i++) {
            d[i] = roundTo10Digits(roundTo10Digits(d[i - 1]) + roundTo10Digits(h));
        }
        double yprev = y;
        int n = 0;
        boolean flag = false;
        while (true) {
            y = roundTo10Digits(roundTo10Digits(y0) + roundTo10Digits(AdaptiveQudrature.getNewtonCotesQuadratureIntegral(func.toFunctionInX(y), d)));
            if (flag) {
                break;
            }
            if (yprev == y) {
                flag = true;
              //  break;
            }
            System.out.println("(x + " + n + "h) = " + roundTo10Digits(roundTo10Digits(x) + roundTo10Digits(n * h)) + ", (y + Δy) = " + roundTo10Digits(y));
            n++;
            yprev = y;
        }
        return y;
    }

    public static void main(String args[]) throws FunctionOneVariable.UndefinedVariableException, AdaptiveQudrature.InsufficientDataPointsException, AdaptiveQudrature.UnequalSpacedDataPointsException, IncorrectFormatException {
        String commands = "";
        try {
            for (String arg : args) {
                commands += arg + " ";
            }
            String[] split = commands.trim().split(";");
            String expression = split[0].trim();
            String x0y0 = split[1].trim();
            double x0 = Double.parseDouble(x0y0.split(",")[0].trim());
            double y0 = Double.parseDouble(x0y0.split(",")[1].trim());

            String limits = split[2].trim();
            double x = Double.parseDouble(limits.split(",")[0].trim());
            double h = Double.parseDouble(limits.split(",")[1].trim());

            FunctionTwoVariable ftw = new FunctionTwoVariable(expression);
            PicardsDifferentialEquationSolver pdf = new PicardsDifferentialEquationSolver(x0, y0, ftw);
            System.out.println(pdf.solveForX(x, h));

        } catch (Exception ex) {
            System.out.println();
            System.out.println(ex.getMessage() + "\n");
            System.out.println(Arrays.toString(ex.getStackTrace()));
            throw new IncorrectFormatException();
        }
    }

    private static class IncorrectFormatException extends Exception {

        public IncorrectFormatException() {
            super("Check that the imput format is correct !!!\nHint : \n\tformat : expression ; x0, y0 ; x , h\nAlso check that the expression you enter is fully parenthesized and does not contain only one term like x.\nPossible formats for expression are : \n ( x + y ) , (0 + sqrt ( x ) ) , ( x + ( ( 2 + 3 ) * ( 4 * 5 ) ) )  , ( ( 1 + sqrt ( pow ( x  y ) ) ) / 2.0 )");
        }
    }

}
