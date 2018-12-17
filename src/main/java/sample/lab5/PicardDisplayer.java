package sample.lab5;

import sample.lab2.Displayer;

public class PicardDisplayer implements Displayer {

    private StringBuilder builder = new StringBuilder();

    public PicardDisplayer(TypeOfPicardDifferentialEquationSolver solver) {
        solver.solveFunction(builder);
    }

    @Override
    public String display() {
        return builder.toString();
    }
}
