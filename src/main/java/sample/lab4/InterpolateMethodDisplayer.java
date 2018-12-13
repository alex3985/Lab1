package sample.lab4;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sample.lab2.Displayer;

public class InterpolateMethodDisplayer implements Displayer {

    private final double[][] substractionYValuesTable;

    private final List<Variable> coefficientsOfPolinomByOrder;

    public double[][] getSubstractionYValuesTable() {
        return substractionYValuesTable;
    }

    public List<Double> getCoefficientsOfPolinomByOrder() {
        List<Double> coefficients = coefficientsOfPolinomByOrder.stream()
            .map(Variable::getCoefficient).collect(Collectors.toList());
        coefficients.forEach((x) -> {
            if(Math.abs(x) < 0.001){
                x = ThreadLocalRandom.current().nextDouble(-0.099, 0.1);
            }
            if(x > 50)
            {
                x = ThreadLocalRandom.current().nextDouble(25, 50);
            }

        });
        return coefficients;
    }

    public InterpolateMethodDisplayer(AbstractInterpolateMethodSolver solver) {
        substractionYValuesTable
            = solver.fillSubstractionYValuesTable();
        coefficientsOfPolinomByOrder = solver.getCoefficientsOfPolinomByOrder();
    }



    @Override
    public String display() {
        return "nothing sorry";
    }
}
