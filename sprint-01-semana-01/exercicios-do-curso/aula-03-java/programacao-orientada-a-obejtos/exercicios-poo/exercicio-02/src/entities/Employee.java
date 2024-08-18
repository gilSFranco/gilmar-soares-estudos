package entities;

public class Employee {
    public String name;
    public double grossSalary;
    public double tax;

    public double netSalary(){
        return grossSalary - tax;
    }

    public void increaseSalary(double percentage){
        double increase = grossSalary * percentage / 100;
        this.grossSalary += increase;
    }

    public String toString(){
        return name + ", R$" + netSalary() + "!\n";
    }
}
