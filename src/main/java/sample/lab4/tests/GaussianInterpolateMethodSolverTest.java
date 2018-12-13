package sample.lab4.tests;

import java.util.List;
import org.junit.Test;
import sample.lab4.AbstractInterpolateMethodSolver;
import sample.lab4.GaussianInterpolateMethodSolver;
import sample.lab4.Polynomial;
import sample.lab4.Variable;

public class GaussianInterpolateMethodSolverTest {

    @Test
    public void getCoefficientsOfPolinom() {
        AbstractInterpolateMethodSolver solver =  new GaussianInterpolateMethodSolver(
            new double[]{-1, 1, -6, -5, -4},
            new double[]{1, 2, 7, 22, 53});
       // double[] expectedParameters = {0.5,0.25,-0.125,0.0625};
        List<Variable> coefficients = solver.getCoefficientsOfPolinomByOrder();
        coefficients.forEach(System.out::println);
        //assertArrayEquals(solver.getCoefficientsOfPolinomByOrder(), expectedParameters,0.0000001);
    }

    @Test
    public void getXMinuxX0DividedByHMultipleByY() {
        GaussianInterpolateMethodSolver solver = new GaussianInterpolateMethodSolver(
            new double[]{2, 4, 6, 8, 10},
            new double[]{2, -2, 10, 20, 70});
        Polynomial xMinuxX0DividedByHMultipleByY = solver
            .getXMinuxX0DividedByHMultipleByY(7, 2);

        List<Variable> variables2 = xMinuxX0DividedByHMultipleByY.getVariables();
        //variables2.forEach(System.out::println);

        Polynomial xMinuxX0DividedByHMultipleByY1 = solver
            .getXMinuxX0DividedByHMultipleByY(3, 2);
        List<Variable> coefficients1 = xMinuxX0DividedByHMultipleByY1.getVariables();
        coefficients1.forEach(System.out::println);

        Polynomial polynomial = xMinuxX0DividedByHMultipleByY
            .multiplyPolynomial(xMinuxX0DividedByHMultipleByY1)
            .multiplyPolynomial(xMinuxX0DividedByHMultipleByY1);

          List<Variable> variables = polynomial.getVariables();

         variables.forEach(System.out::println);

    }
}
