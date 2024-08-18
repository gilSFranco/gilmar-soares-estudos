package entities;

public class Student {
    public String name;
    public double nota1;
    public double nota2;
    public double nota3;

    public double calculateAnnualNote(){
        return nota1 + nota2 + nota3;
    }

    public String studentStatus(){
        String status;
        double missing;

        if(calculateAnnualNote() >= 60.0){
            status = "Pass!";
        } else{
            missing = 60.0 - calculateAnnualNote();
            status = "Failed! Missing " + missing + " points only :(";
        }

        return status;
    }

    public String toString(){
        return "\nFinal grade: " + String.format("%.2f", calculateAnnualNote()) + "!\nYou..." + studentStatus();
    }
}
