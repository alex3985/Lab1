package sample.lab4.tests;

import java.util.List;
import sample.lab4.AbstractInterpolateMethodSolver;
import sample.lab4.Variable;

public class TestInterpolateTableSolver extends AbstractInterpolateMethodSolver {

    public TestInterpolateTableSolver(double[] initialX, double[] initialY) {
        super (initialX, initialY);
    }

    @Override
    public List<Variable> getCoefficientsOfPolinomByOrder() {
        return null;
    }
}
