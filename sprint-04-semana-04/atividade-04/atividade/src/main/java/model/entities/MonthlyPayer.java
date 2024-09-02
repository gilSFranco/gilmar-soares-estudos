package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MonthlyPayer {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected Integer codeMonthlyPayer;
    private Double monthlyFee;
    private LocalDate currentMonth;
    private Boolean monthlyFeePaid;

    public MonthlyPayer() {
    }

    public MonthlyPayer(Integer codeMonthlyPayer, Double monthlyFee, LocalDate currentMonth, Boolean monthlyFeePaid) {
        this.codeMonthlyPayer = codeMonthlyPayer;
        this.monthlyFee = monthlyFee;
        this.currentMonth = currentMonth;
        this.monthlyFeePaid = monthlyFeePaid;
    }

    public Integer getCodeMonthlyPayer() {
        return codeMonthlyPayer;
    }

    public void setCodeMonthlyPayer(Integer codeMonthlyPayer) {
        this.codeMonthlyPayer = codeMonthlyPayer;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public LocalDate getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(LocalDate currentMonth) {
        this.currentMonth = currentMonth;
    }

    public Boolean getMonthlyFeePaid() {
        return monthlyFeePaid;
    }

    public void setMonthlyFeePaid(Boolean monthlyFeePaid) {
        this.monthlyFeePaid = monthlyFeePaid;
    }

    @Override
    public String toString() {
        return "MonthlyPayer{" +
                "codeMonthlyPayer=" + codeMonthlyPayer +
                ", monthlyFee=" + monthlyFee +
                ", currentMonth=" + formatter.format(currentMonth) +
                ", monthlyFeePaid=" + monthlyFeePaid +
                '}';
    }
}
