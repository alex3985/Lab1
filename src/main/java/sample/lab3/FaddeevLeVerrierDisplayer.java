package sample.lab3;

import sample.lab2.Displayer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Класс для отобрадение ильтераций метода Лаверье-Фадеева
 */
public class FaddeevLeVerrierDisplayer implements Displayer {
    /**
     * Очередь элемнтов матрицы А для каждой ильтерации
     */
    Deque<Double> matrixA;
    /**
     * Очередь элемнтов матрицы B для каждой ильтерации
     */
    Deque<Double> matrixB;
    /**
     * Очередь коэффициентов многочлена
     */
    Deque<Double> coefficients;
    /**
     * Очередь собственных значений матрицы
     */
    Deque<Double> roots;
    StringBuilder builder;

    /**
     * Конструктор который иницилизирует поля класса
     */
    public FaddeevLeVerrierDisplayer() {
        this.matrixA = new LinkedList<>();
        this.matrixB = new LinkedList<>();
        this.coefficients = new LinkedList<>();
        this.roots = new LinkedList<>();
        this.builder = new StringBuilder();
    }

    public Deque<Double> getMatrixA() {
        return matrixA;
    }

    public Deque<Double> getMatrixB() {
        return matrixB;
    }

    public Deque<Double> getCoefficients() {
        return coefficients;
    }

    public Deque<Double> getRoots() {
        return roots;
    }

    /**
     * Метод для отображения матрицы
     * @param matrix - матрица, которую нужно отобразить
     */
    private void displayMatrix(Deque<Double> matrix) {
        for (int i = 0; i < 9; ) {
            for (int j = 0; j < 3; j++, i++)
                this.builder.append(matrix.pop() + " ");
            this.builder.append('\n');
        }
    }

    /**
     * Метод который отображает все ильтерации в одной строке
     * @return - возвращяет строку в которой записаны все ильтерации
     */
    @Override
    public String display() {
        for (int i = 1; i <= 3; i++) {
            builder.append("A" + i + ":\n");
            this.displayMatrix(matrixA);
            builder.append("SpA" + i + "/" + i + "=q" + i + "= " + (-1)*Double.valueOf((Double)coefficients.toArray()[i-1]) + '\n');
            builder.append("B" + i + "=A" + i + "-q" + i + "E:\n");
            this.displayMatrix(matrixB);
        }
        for (int i = 1; i<=3; i++)
            builder.append("p" + i + "=-1*q" + i + "=" + (-1)*Double.valueOf((Double)coefficients.toArray()[i-1]) + '\n');
        for (int i=1;i<=3;i++) {
            if (i == 1)
                builder.append("\u03bb" + "^3" + " * (" + coefficients.pop() + ") * ");
            if(i==2)
                builder.append("\u03bb"+"^2"+" + ("+coefficients.pop()+") * ");
            if(i==3)
               builder.append("\u03bb"+" + (" +coefficients.pop()+")\n");
        }
        for (int i = 1; !roots.isEmpty(); i++) {
            builder.append("\u03bb" + i + "=" + roots.pop() + "\n");
        }
        return builder.toString();
    }
}
