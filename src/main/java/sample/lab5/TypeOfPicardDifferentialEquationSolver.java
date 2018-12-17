package sample.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TypeOfPicardDifferentialEquationSolver {
    private final Function<Double,Double> functionVariant;
    private double x0;
    private double y0;
    private double b;
    private double h;

    public TypeOfPicardDifferentialEquationSolver(
        Function<Double, Double> functionVariant,
        double x0, double y0, double b, double h) {
        this.functionVariant = functionVariant;
        this.x0 = x0;
        this.y0 = y0;
        this.b = b;
        this.h = h;
    }

    public List<Double> solveFunction(StringBuilder displayBuilder){
        List<Double> listOfY = new ArrayList<>();
        for (double i = x0; i < b;i+=h){
            Double valueOfFunctionVariant = functionVariant.apply(i);
            listOfY.add(valueOfFunctionVariant);
            displayBuilder.append("x = ")
                .append(i)
                .append(" ; y = ")
                .append(valueOfFunctionVariant)
                .append("\n");
        }
    return listOfY;
    }
}
