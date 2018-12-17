package sample.lab5;

import static java.lang.Math.log;
import static java.lang.StrictMath.pow;
import static org.junit.Assert.*;

import java.util.function.Function;
import org.junit.Test;

public class TypeOfPicardDifferentialEquationSolverTest {

    @Test
    public void testMethod(){
        Function <Double,Double> function = x -> 1 + (pow(x,3)/81)+(pow(x,2)/6)
            +(4*x)/3+(x/3+1.0/2)*log(x)*log(x)+((-x*x)/9-(4*x)/3)*log(x)-log(x) - 1.5;
        TypeOfPicardDifferentialEquationSolver solver =
            new TypeOfPicardDifferentialEquationSolver(function,1,1,5,0.1);
        StringBuilder builder = new StringBuilder();
        solver.solveFunction(builder);
        System.out.println(builder.toString());

    }

}