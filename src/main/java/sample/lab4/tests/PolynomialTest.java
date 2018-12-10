package sample.lab4.tests;

import java.util.List;
import org.junit.Test;
import sample.lab4.Polynomial;
import sample.lab4.Variable;

public class PolynomialTest {

    @Test
    public void getCoefficients() {
        Polynomial polynomial = new Polynomial();
        polynomial.addVariable(new Variable(3,1));
        polynomial.addVariable(new Variable(3,-1));
        polynomial.addVariable(new Variable(7,(-23.0/5)));
        polynomial.addVariable(new Variable(2,42));
        polynomial.addVariable(new Variable(2,-1));
        Variable mult = new Variable(4,1.0/3);
        Variable mult2 = new Variable(3,1.0/3);
        mult.multipleTo(mult2);
        polynomial.addVariable(mult);
        List<Double> coefficients = polynomial.getCoefficients();
        coefficients.forEach(System.out::println);
    }
}
