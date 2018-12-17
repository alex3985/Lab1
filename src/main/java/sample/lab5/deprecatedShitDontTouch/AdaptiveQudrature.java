package sample.lab5.deprecatedShitDontTouch;

public strictfp class AdaptiveQudrature extends Maths {

    private static class NewtonCotesQuadrature {

        Double datax[], datay[];

        public void setDataPoints(Double[] x, Double[] y) throws UnequalSpacedDataPointsException, InsufficientDataPointsException {
            datax = x;
            datay = y;
            if (x.length < 2) {
                throw new InsufficientDataPointsException();
            }
            //check whether the data points are equally spaced
            double h = x[1] - x[0];
            for (int i = 2; i < x.length; i++) {
                if (x[i] - x[i - 1] != h) {
                    throw new UnequalSpacedDataPointsException();
                }
            }
        }

        public void setDataPoints(Double[] x, FunctionOneVariable f) throws FunctionOneVariable.UndefinedVariableException, InsufficientDataPointsException, UnequalSpacedDataPointsException {
            datax = x;
            datay = new Double[x.length];
            int j = 0;
            for (double X : datax) {
                datay[j++] = f.evaluate(X);
            }

            if (x.length < 2) {
                throw new InsufficientDataPointsException();
            }
            //check whether the data points are equally spaced
            double h = roundTo10Digits(roundTo10Digits(x[1]) - roundTo10Digits(x[0]));
            for (int i = 2; i < x.length; i++) {
                if (roundTo10Digits(roundTo10Digits(x[i]) - roundTo10Digits(x[i - 1])) - h > 1e6) {
                    System.out.println(roundTo10Digits(roundTo10Digits(x[i]) - roundTo10Digits(x[i - 1])));
                    throw new UnequalSpacedDataPointsException();
                }
            }
        }

        public double integrate() {
            double area;
            if ((datax.length - 1) % 3 == 0 && datax.length - 1 != 0) {
                System.out.println("Using Simson's 3/8th rule !!");
                double h = (datax[1] - datax[0]);
                area = 3.0 * h / 8.0;
                double summation = 0;
                //calculate summation here
                double sum1 = datay[0] + datay[datay.length - 1];
                double sum2 = 0, sum3 = 0;
                for (int i = 1; i < datax.length - 1; i++) {
                    if (i % 3 == 0) {
                        sum3 += datay[i];
                    } else {
                        sum2 += datay[i];
                    }
                }
                sum2 *= 2.0;
                summation = sum1 + sum2 + sum3;

                area *= summation;
            } else if ((datax.length - 1) % 2 == 0 && datax.length - 1 != 0) {
             //   System.out.println("Using Simson's 1/3rd rule !!");
                double h = (datax[1] - datax[0]);
                area = h / 3.0;
                double summation = 0;
                //calculate summation here
                double sum1 = datay[0] + datay[datay.length - 1];
                double sum2 = 0, sum3 = 0;
                for (int i = 1; i < datax.length - 1; i++) {
                    if (i % 2 == 0) {
                        sum3 += datay[i];
                    } else {
                        sum2 += datay[i];
                    }
                }
                sum2 *= 4.0;
                sum3 *= 2.0;
                summation = sum1 + sum2 + sum3;
                area *= summation;
            } else {
           //     System.out.println("Using Trapezoidal rule by default !!");
                double h = (datax[1] - datax[0]);
                area = h / 2.0;
                double summation = 0;
                //calculate summation here
                double sum1 = datay[0] + datay[datay.length - 1];
                double sum2 = 0;
                for (int i = 1; i < datax.length - 1; i++) {
                    sum2 += datay[i];
                }
                sum2 *= 2.0;
                summation = sum1 + sum2;
                area *= summation;
            }
            return area;
        }
    }

    public static class UnequalSpacedDataPointsException extends Exception {

        public UnequalSpacedDataPointsException() {
            super("The data points are required to be equal spaced !!! \nHint : Check your x values");
        }
    }

    public static class InsufficientDataPointsException extends Exception {

        public InsufficientDataPointsException() {
            super("There should be two or more data points to evaluate !!");
        }
    }

    public static double getNewtonCotesQuadratureIntegral(FunctionOneVariable f, Double[] x) throws FunctionOneVariable.UndefinedVariableException, InsufficientDataPointsException, UnequalSpacedDataPointsException {
        NewtonCotesQuadrature ncqa = new NewtonCotesQuadrature();
        ncqa.setDataPoints(x, f);
        return ncqa.integrate();
    }
}