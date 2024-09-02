package model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Detached {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private String plate;
    private Double amountToPay;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;

    public Detached() {
    }

    public Detached(String plate, Double amountToPay, LocalDateTime entryTime, LocalDateTime departureTime) {
        this.plate = plate;
        this.amountToPay = amountToPay;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Detached{" +
                "plate='" + plate + '\'' +
                ", amountToPay=" + amountToPay +
                ", entryTime=" + formatter.format(entryTime) +
                ", departureTime=" + formatter.format(departureTime) +
                '}';
    }
}
