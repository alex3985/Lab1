package sample.lab5;

public class Adams {
    final static private int SIZE = 4;
    StringBuilder builder;
    private double[] arrayX;
    private double[] arrayY;
    private double[] arrayQ;
    private double[] arrayYdiv;
    private double[] arrayQ1;
    private double[] arrayQ2;
    private double[] arrayQ3;
    private double Q4;
    private double a;
    private double b;
    private double h;

    public Adams(double a, double b, double y, double x) {
        builder = new StringBuilder();
        this.a = a;
        this.b = b;
        this.arrayX = new double[SIZE + 1];
        this.arrayY = new double[SIZE + 1];
        double j = a;
        this.h = (b - a) / SIZE;
        for (int i = 0; i < arrayX.length; i++, j += this.h) {
            arrayX[i] = j;
            builder.append("x" + i + "= " + arrayX[i] + "\n");
        }
        this.arrayY[0] = y;
        builder.append("y" + 0 + "= " + arrayY[0] + "\n");
        System.err.println("x= " + arrayX[0] + " y= " + arrayY[0]);
        for (int i = 1; i < this.arrayX.length; i++) {
            this.arrayY[i] = eulerMethod(this.arrayX[i - 1], this.arrayY[i - 1]);
            builder.append("y" + i + "= " + arrayY[i] + "\n");
        }
        this.arrayQ = new double[SIZE + 1];
        this.arrayQ1 = new double[SIZE];
        this.arrayQ2 = new double[SIZE - 1];
        this.arrayQ3 = new double[SIZE - 2];
    }

    static double function1(double x, double y) {
        return (y * y * Math.log(x) - y) / x;
    }

    private double eulerMethod(double x, double y) {
        return y - h * function1(x, y);

    }

    private void findAllQ() {
        System.err.println("-----------");
        builder.append("----------------------\n");
        for (int i = 0; i < arrayQ.length; i++) {
            arrayQ[i] = h * function1(arrayX[i], arrayY[i]);
            System.err.println("q= " + arrayQ[i]);
            builder.append("q" + (i) + "= " + arrayQ[i]);
        }
        System.err.println("-----------");
        builder.append("----------------------\n");
        for (int i = 0; i < arrayQ1.length; i++) {
            arrayQ1[i] = arrayQ[i + 1] - arrayQ[i];
            builder.append("\u03bbq" + (i) + "= " + arrayQ[i]+"\n");
            System.err.println("q1= " + arrayQ1[i]);
        }
        System.err.println("-----------");
        builder.append("----------------------\n");
        for (int i = 0; i < arrayQ2.length; i++) {
            arrayQ2[i] = arrayQ1[i + 1] - arrayQ1[i];
            builder.append("\u03bb^2q" + (i) + "= " + arrayQ[i]+"\n");
            System.err.println("q2= " + arrayQ2[i]);
        }
        System.err.println("-----------");
        builder.append("----------------------\n");
        for (int i = 0; i < arrayQ3.length; i++) {
            arrayQ3[i] = arrayQ2[i + 1] - arrayQ2[i];
            builder.append("\u03bb^3q" + (i) + "= " + arrayQ[i]+"\n");
            System.err.println("q3= " + arrayQ3[i]);
        }
        System.err.println("-----------");
        builder.append("----------------------\n");
        Q4 = arrayQ3[1] - arrayQ3[0];
        builder.append("\u03bb^4q" + (0) + "= " + Q4+"\n");
        System.err.println("q4= " + Q4);
        builder.append("----------------------\n");
    }

    public String solve() {
        this.findAllQ();
        double d = arrayY[SIZE] + arrayQ[SIZE] + 0.5 * arrayQ1[SIZE - 1] + 5.0 / 12.0 * arrayQ2[SIZE - 2] + 3.0 / 8.0 * arrayQ3[SIZE - 3] + 251.0 / 720.0 * Q4;
        builder.append("y = y4 + q4 + (1/2)\u03bbq3 + \u03bb^2q2 + \u03bb^3q1 + \u03bb^4q0 = " + d);
        System.err.println("d= " + d);
        return builder.toString();
    }

    public static void main(String[] args) {
        new Adams(1, 5, 0.5, 1).solve();
    }
}
