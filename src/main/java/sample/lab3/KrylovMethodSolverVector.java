package sample.lab3;

import java.util.ArrayList;
import java.util.List;

// Класс, функции, которого искать собстсвенный вектор матрицы
public class KrylovMethodSolverVector {

    private double matrix[][];
    private double[] coefficientsP;
    private double[][] q;

    public KrylovMethodSolverVector(double[][] matrix, double[] coefficientsP) {
        this.matrix = matrix;
        this.coefficientsP = coefficientsP;
    }

    public static Double[] sum(Double[] a, double[] b) {
        Double[] res = new Double[3];
        for (int i = 0; i < 3; i++) {
            res[i] = a[i] + b[i];
        }
        return res;
    }

    public static double[] mulnum(double[] a, double b) {
        double[] res = new double[3];
        for (int i = 0; i < 3; i++) {
            res[i] = a[i] * b;
        }
        return res;
    }
// метод, который исчет вектор матрицы
    public List<Double[]> vec(Double[] x) {
        int n = coefficientsP.length;
        double[] y0 = new double[]{0, 0, 1};

        q = new double[n][n];
        q[0][0] = 1;
        q[1][0] = 1;
        q[2][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                System.out.println("q("+i+","+j+") = "+x+"("+i+") * " +"q("+i+","+j+")");
                q[i][j] = x[i] * q[i][j - 1] + coefficientsP[i];

            }
        }

        List<Double[]> vector = new ArrayList<Double[]>();
        double[] tmp = y0;

        for (int k = 0; k < n; k++) {
            Double[] v = new Double[3];
            v[0] = 0d;
            v[1] = 0d;
            v[2] = 0d;
            for (int j = n - 1; j > 0; j--) {
                v = sum(v, mulnum(tmp, q[k][j]));
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    s.append(v[i]).append(" ");
                }
                tmp = multiplicateMatrixNMToMatrixM(matrix, tmp);
            }
            v = sum(v, tmp);
            vector.add(v);
        }
        normalizeMatrix(vector);
        return vector;
    }

    private void normalizeMatrix(List<Double[]> a) {
        for (int i = 0; i < a.size(); i++) {
            Double no = 0.0;
            for (int j = 0; j <  a.size(); j++) {
                no += a.get(i)[j] * a.get(i)[j];
            }
            no = Math.sqrt(no);
            no = 1.0 / no;
            for (int j = 0; j < a.size(); j++)
            {
                a.get(i)[j]=no * a.get(i)[j];
            }
        }
    }

    private double[] multiplicateMatrixNMToMatrixM(double[][] matrixNM, double[] matrixN) {
        int n = matrixNM.length;
        double[] resultOfMultiplicationMatrix = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultOfMultiplicationMatrix[i] += matrixNM[i][j] * matrixN[j];
            }
        }
        return resultOfMultiplicationMatrix;
    }

    public double[][] getQ() {
        return q;
    }

}
