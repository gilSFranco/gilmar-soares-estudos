package model.entities;

import java.util.Optional;

public class Vehicle {
    private String plate;
    private Boolean exempted;
    protected Category category;
    protected MonthlyPayer monthlyPayer;

    public Vehicle() {
    }

    public Vehicle(String plate, Boolean exempted, Category category) {
        this.plate = plate;
        this.exempted = exempted;
        this.category = category;
    }

    public Vehicle(String plate, Boolean exempted, Category category, MonthlyPayer monthlyPayer) {
        this.plate = plate;
        this.exempted = exempted;
        this.category = category;
        this.monthlyPayer = monthlyPayer;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Boolean getExempted() {
        return exempted;
    }

    public void setExempted(Boolean exempted) {
        this.exempted = exempted;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MonthlyPayer getMonthlyPayer() {
        return monthlyPayer;
    }

    public void setMonthlyPayer(MonthlyPayer monthlyPayer) {
        this.monthlyPayer = monthlyPayer;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plate='" + plate + '\'' +
                ", exempted=" + exempted +
                ", category=" + category +
                '}';
    }
}
