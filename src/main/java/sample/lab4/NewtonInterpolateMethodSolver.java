package sample.lab4;

import java.util.List;

public class NewtonInterpolateMethodSolver extends AbstractInterpolateMethodSolver {

    public NewtonInterpolateMethodSolver(double[] initialX, double[] initialY) {
        super(initialX, initialY);
    }

    @Override
    public List<Variable> getCoefficientsOfPolinomByOrder() {
        double[][] tableSubstraction = fillSubstractionYValuesTable();
        int averageIndex = (tableSubstraction.length) / 2;
        List<Variable> coefficients;
        Polynomial polynomial = new Polynomial();

        polynomial
            .addVariable(new Variable(0, tableSubstraction[0][averageIndex]))

            .addPolynomal(getXMinuxX0DividedByHMultipleByY(
                tableSubstraction[1][averageIndex], 1))// (x-x0)/h * ^y_(-2)

            .addPolynomal(getXMinuxX0DividedByHMultipleByY(
                tableSubstraction[2][averageIndex], 2)
                .multiplyPolynomial(getXMinuxX0DividedByHMultipleByY(
                    tableSubstraction[2][averageIndex], 2)))// (x^2-2*x*x0-x0^2)/h^2 * ^2y_(-2)
            .addPolynomal
                (getXMinuxX0DividedByHMultipleByY(tableSubstraction[3][averageIndex], 6)
                    .addVariable(new Variable(0, -1))
                    .multiplyPolynomial(getXMinuxX0DividedByHMultipleByY
                        (tableSubstraction[3][averageIndex - 1], 6)
                        .addVariable(new Variable(0, -2)))
                    .multiplyPolynomial(
                        getXMinuxX0DividedByHMultipleByY(tableSubstraction[3][averageIndex],
                            6)));

        coefficients = polynomial.getVariables();
        return coefficients;
    }
}
