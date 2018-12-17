package sample.lab5.deprecatedShitDontTouch.shittyTestsPicard;

import org.junit.Test;
import sample.lab5.deprecatedShitDontTouch.AdaptiveQudrature;
import sample.lab5.deprecatedShitDontTouch.AdaptiveQudrature.InsufficientDataPointsException;
import sample.lab5.deprecatedShitDontTouch.AdaptiveQudrature.UnequalSpacedDataPointsException;
import sample.lab5.deprecatedShitDontTouch.FunctionOneVariable;
import sample.lab5.deprecatedShitDontTouch.FunctionOneVariable.UndefinedVariableException;

public class AdaptiveQudratureTest {

    @Test
    public void getNewtonCotesQuadratureIntegral()
        throws UnequalSpacedDataPointsException, UndefinedVariableException, InsufficientDataPointsException {
        AdaptiveQudrature ncqa = new AdaptiveQudrature();
        FunctionOneVariable toIntegerate = new FunctionOneVariable("( pow ( x ) + sqrt ( x ) )");
        //System.out.println(ncqa.integrate());
        double newtonCotesQuadratureIntegral = AdaptiveQudrature
            .getNewtonCotesQuadratureIntegral(toIntegerate, new Double[]{0.0, 1.0, 2.0, 3.0});
        System.out.println(newtonCotesQuadratureIntegral);
    }
}