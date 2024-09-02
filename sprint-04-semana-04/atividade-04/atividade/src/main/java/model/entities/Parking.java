package model.entities;

import java.util.Arrays;

public class Parking {
    private Boolean[] vacancy;
    private Integer countVacancies;

    public Parking() {
    }

    public Parking(Boolean[] vacancy, Integer countVacancies) {
        this.vacancy = vacancy;
        this.countVacancies = countVacancies;
    }

    public Boolean[] getVacancy() {
        return vacancy;
    }

    public void setVacancy(Boolean[] vacancy) {
        this.vacancy = vacancy;
    }

    public Integer getCountVacancies() {
        return countVacancies;
    }

    public void setCountVacancies(Integer countVacancies) {
        this.countVacancies = countVacancies;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "vacancy=" + Arrays.toString(vacancy) +
                ", countVacancies=" + countVacancies +
                '}';
    }
}
