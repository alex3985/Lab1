package sample.lab1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static java.lang.Math.abs;

public class GaussSeidel {

    public  final int MAX_ITERATIONS = 20;
    private double[][] M;
    private StringBuilder arrayB;
    private StringBuilder arrayX;


    public  GaussSeidel(double[][] matrix) {
        M = matrix;
        arrayX = new StringBuilder();
        arrayB = new StringBuilder();

    }

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public void print() {
        int n = M.length;
        for (double[] aM : M) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(aM[j] + " ");
            }
            System.out.println();

        }
    }

    public boolean transformToDominant(int r, boolean[] V, int[] R) {
        int n = M.length;
        if (r == M.length) {
            double[][] T = new double[n][n + 1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++) {

                    T[i][j] = M[R[i]][j];
                }
            }
            M = T;
            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) {
                continue;
            }

            double sum = 0;

            for (int j = 0; j < n; j++) {

                sum += Math.abs(M[i][j]);

            }

            if (2 * Math.abs(M[i][r]) > sum) { // diagonally dominant?
                V[i] = true;
                R[r] = i;
                System.out.println(R[r]);
                if (transformToDominant(r + 1, V, R)) {
                    return true;
                }

                V[i] = false;
            }
        }

        return false;
    }

    public boolean makeDominant() {
        boolean[] visited = new boolean[M.length];
        int[] rows = new int[M.length];

        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }

    public StringBuilder getArrayB() {
        return arrayB;
    }

    public void setArrayB(StringBuilder arrayB) {
        this.arrayB = arrayB;
    }

    public StringBuilder getArrayX() {
        return arrayX;
    }

    public void setArrayX(StringBuilder arrayX) {
        this.arrayX = arrayX;
    }

    public double[] solve() {

        int iterations = 0;
        print();
        int n = M.length;
        double epsilon = 0.01;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);

        for (int i = 0; i < n; i++) {
            double sum = M[i][n]; // b_n
            double c = 0;
            double d = 0;
            for (int j = 0; j < n; j++) {

                if (j != i) {
                    c = round(-(M[i][j])/M[i][i],3);

                    arrayX.append("c_")
                        .append(i + 1)
                        .append(j + 1)
                        .append(" =")
                        .append(c)
                        .append(" \n");

                }
            }
            d = round(sum  /M[i][i], 3);
            arrayX.append("d_")
                .append(i + 1)
                .append(" =")
                .append(d)
                .append(" \n");
            // Update x_i bForLab2 use in the next row calculation

            X[i] = round(1 / M[i][i] * sum, 3);

        }

        while (true) {
            arrayX.append("Iteration ")
                .append(iterations + 1)
                .append(" :\n");
            for (int i = 0; i < n; i++) {
                arrayX.append("x_")
                    .append(i + 1)
                    .append("= ");
                double sum = M[i][n]; // b_n

                for (int j = 0; j < n; j++) {

                    if (j != i) {
                        sum = round(sum - M[i][j] * X[j], 3);
                        arrayX.append("+c_")
                            .append(i + 1)
                            .append(j + 1)
                            .append("*X")
                            .append(j + 1)
                            .append(" + ");
                    }
                }
                // Update x_i bForLab2 use in the next row calculation

                X[i] = round(1 / M[i][i] * sum, 3);
                arrayX.append("d_")
                    .append(i + 1)
                    .append(" =")
                    .append(X[i])
                    .append("\n");

            }
            iterations++;
            if (iterations == 1) {
                continue;
            }

            boolean stop = true;
            for (int i = 0; i < n && stop; i++) {
                if (Math.abs(X[i] - P[i]) > epsilon) {
                    stop = false;
                }
            }

            if (stop || iterations == MAX_ITERATIONS) {
                break;
            }
            P = (double[]) X.clone();
        }

        return X;
    }

}