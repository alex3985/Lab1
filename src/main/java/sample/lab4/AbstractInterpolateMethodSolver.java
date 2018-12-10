package sample.lab4;

import java.util.List;

abstract public class AbstractInterpolateMethodSolver {

    private final Variable x;
    private final Variable x0;
    private final double h;
    private double[][] substractionYValuesTable;

    public AbstractInterpolateMethodSolver(double[] initialX, double[] initialY) {
        substractionYValuesTable = new double[initialX.length][initialX.length];
        h = initialX[1] - initialX[0];
        x0 = new Variable(0, (-initialX[0]));
        x = new Variable(1, 1);
        substractionYValuesTable[0] = initialY;
    }

    public double getH() {
        return h;
    }

    public Variable getX0() {
        return x0;
    }

    public Variable getX() {
        return x;
    }

    public abstract List<Variable> getCoefficientsOfPolinomByOrder();

    public double[][] fillSubstractionYValuesTable() {
        int offsetCounter = 1;
        for (int i = 1; i < substractionYValuesTable.length; i++, offsetCounter++) {
            for (int j = 0; j < substractionYValuesTable.length - offsetCounter; j++) {
                substractionYValuesTable[i][j] = calculateYSubstractionValue(i, j);
            }
        }
        return substractionYValuesTable;
    }

    private double calculateYSubstractionValue(int i, int j) {
        return substractionYValuesTable[i - 1][j + 1] - substractionYValuesTable[i - 1][j];
    }

    public Polynomial getXMinuxX0DividedByHMultipleByY(double y, double divisionFactorial) {

        Polynomial polynomial1 = new Polynomial();
        Variable x0 = new Variable(getX0());
        Variable x = new Variable(getX());
        x0.multipleTo(new Variable(0,
            y / (getH())));
        x.multipleTo(new Variable(0,
            y / (getH())));
        polynomial1
            .addVariable(x0
                .multipleTo(new Variable(0, 1 / divisionFactorial)))
            .addVariable(x
                .multipleTo(new Variable(0, 1 / divisionFactorial)));

        return polynomial1;
    }


}
