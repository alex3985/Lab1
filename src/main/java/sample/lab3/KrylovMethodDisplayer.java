package sample.lab3;

import java.util.Arrays;
import sample.lab2.Displayer;

/**
 * Класс для отображение итераций метода Крылова поиска собсвтенных значений матрицы
 */
public class KrylovMethodDisplayer implements Displayer {


    private StringBuilder matrixValuesReport = new StringBuilder();


    public KrylovMethodDisplayer(KrylovMethodSolver solver, FaddeevLeVerrierDisplayer solver2) {
        setMatrixValuesReport(solver,solver2);
    }

    private void setMatrixValuesReport(KrylovMethodSolver solver, FaddeevLeVerrierDisplayer solver2) {
        addReportAboutYToStringBuilder(solver.getY());
        addSystemOfEquationsToBuilder(solver.getY(), solver.getYn());
        solver.setCoefficientsP();
        addLambdaEquationToBuilder(solver.getCoefficientsP());
        matrixValuesReport.append(Arrays.toString(solver2.getRoots().toArray()));
    }

    private void addLambdaEquationToBuilder(double[] coefficientsP) {
        matrixValuesReport.append("\n");
        int k = coefficientsP.length - 1;
        matrixValuesReport.append("\u03bb^3 ");
        for (int i = 0; i < coefficientsP.length; i++,k--) {
            String formattedCell = String.format(" %s*\u03bb^%s ", coefficientsP[i],k);
            matrixValuesReport.append(formattedCell);
        }
        matrixValuesReport.append(" ");
        matrixValuesReport.append(coefficientsP[2]);
        matrixValuesReport.append("= 0");
        matrixValuesReport.append("\n");
    }

    private void addReportAboutYToStringBuilder(double[][] y) {
      int k = y.length-1;
        for (int i = 0; i < y.length; i++,k--) {
            matrixValuesReport.append("\n");
            matrixValuesReport.append("y_").append(k).append("= ");
            for (int j = 0; j < y.length; j++) {
                String formattedCoefficient = String.format("              | %s | ", y[j][i]);
                matrixValuesReport.append(formattedCoefficient);
                matrixValuesReport.append("\n");
            }
        }
    }

    private void addSystemOfEquationsToBuilder(double[][] y, double[] yn) {
        matrixValuesReport.append("\n");
        for (int i = 0; i < y.length; i++) {
            int k = 1;
            for (int j = y.length - 1 ; j >= 0; j--) {
                String formattedCoefficient = String.format(" %s*p_%s ", y[i][j], k);
                k++;
                matrixValuesReport.append(formattedCoefficient);
            }
            matrixValuesReport.append("= ")
                .append(yn[i]);
            matrixValuesReport.append(";\n");
        }
        matrixValuesReport.append("\n");
    }

    @Override
    public String display() {
        return matrixValuesReport.toString();
    }
}
