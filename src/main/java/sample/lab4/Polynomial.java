package sample.lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Polynomial {
    private List<Variable> variables;

    public List<Variable> getVariables() {
    //  variables.forEach(System.out::println);
        return variables;
    }

    public Polynomial() {
        variables = new ArrayList<>();
    }

    public Polynomial addVariable(Variable variable){
        if(checkIfContainsVariable(variable)){
            variables.stream()
                .filter(x -> x.getPower() == variable.getPower())
                .forEach(x -> x.addTo(variable));
        }
        else
            variables.add(variable);
        return this;
    }

    private void makeAnActionWithVariableList(Function<Variable,Variable> func, Variable variable){
        variables.stream()
            .filter(x -> x.getPower() == variable.getPower())
            .forEach(func::apply);
    }

    private boolean checkIfContainsVariable(Variable variable){
        List<Integer> collect = variables.stream()
            .map(Variable::getPower)
            .collect(Collectors.toList());
        return collect.contains(variable.getPower());
    }

    public Polynomial addPolynomal(Polynomial polynomial){
        for (Variable x: polynomial.getVariables()){
             addVariable(x);
        }
        return this;
    }

    public Polynomial multiplyPolynomial(double multValue){
        for (Variable x: this.getVariables()){
            x.multipleTo(new Variable(0,multValue));
        }
        return this;
    }

    public Polynomial multiplyPolynomial(Polynomial polynomial){
      List<Variable> variables = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial();
        for (Variable x: polynomial.getVariables()){
           for (Variable x1: getVariables()){
               Variable variable = new Variable(x1);
               polynomial1.addVariable(variable.multipleTo(x));
            //   variables.add(variable.multipleTo(x));
            //   System.out.println(variable);
              // System.out.println();
           }
        }
     //   variables.forEach(this::addVariable);
        return  polynomial1;
    }

    public  List<Double> getCoefficients(){
        return variables.stream()
            .sorted(Comparator.comparingInt(Variable::getPower))
            .map(Variable::getCoefficient)
            .collect(Collectors.toList());
    }


}
