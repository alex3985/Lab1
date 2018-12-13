package sample.lab4;

public class Variable {
    private int power;
    private double coefficient;

    public Variable(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    public Variable(Variable variable) {
        this.power = variable.getPower();
        this.coefficient = variable.getCoefficient();
    }


    public Variable multipleTo(Variable variable){
        this.coefficient = variable.getCoefficient() * this.coefficient;
        this.power = variable.getPower() + this.power;
        return this;
    }

    public Variable addTo(Variable variable){
        if (variable.getPower() == this.getPower())
        this.coefficient = variable.getCoefficient() + this.coefficient;
        return this;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Variable{" +
            "power=" + power +
            ", coefficient=" + coefficient +
            '}';
    }
}
