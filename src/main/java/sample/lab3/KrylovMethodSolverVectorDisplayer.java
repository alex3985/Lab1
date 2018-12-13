package sample.lab3;

import java.util.List;
import sample.lab2.Displayer;

/**
 * Класс для отображение итераций метода Крылова поиска собсвтенных векторов матрицы
 */
public class KrylovMethodSolverVectorDisplayer implements Displayer {
    private StringBuilder matrixValuesReport = new StringBuilder();

    public KrylovMethodSolverVectorDisplayer(KrylovMethodSolverVector solver,Double[] x) {
        setMatrixValuesReport(solver,x);
    }

    private void setMatrixValuesReport(KrylovMethodSolverVector solver,Double[] x) {
        List<Double[]> vec = solver.vec(x);
        addQToStringBuilder(solver);
        int j = 1;
        for (Double[] line:vec){
            matrixValuesReport.append("x_").append(j).append("=");
            for (Double aLine : line) {
                String formattedCoefficient = String.format("             | %s | ", aLine);
                matrixValuesReport.append(formattedCoefficient);
                matrixValuesReport.append("\n");
            }
            j++;
        }
    }

    private void addQToStringBuilder(KrylovMethodSolverVector solver){
        double[][] q = solver.getQ();
        matrixValuesReport.append("\n");
        for (int i=0;i<q.length;i++){
            for (int j=0;j<q.length;j++){
                matrixValuesReport.append("q[")
                    .append(i)
                    .append(",")
                    .append(j)
                    .append("]= ")
                    .append(q[i][j]).append(" ");
            }
        matrixValuesReport.append("\n");
        }
    }


    @Override
    public String display() {
        return matrixValuesReport.toString();
    }
}
