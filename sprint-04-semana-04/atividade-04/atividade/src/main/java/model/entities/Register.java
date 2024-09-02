package model.entities;

public class Register implements Registration {
    private Integer codeRegistration;
    private Integer entranceCancel;
    private Integer exitCancel;
    private Integer vacancy;
    private Vehicle vehicle;
    private Parking parking;

    public Register() {
    }

    public Register(Integer codeRegistration, Integer entranceCancel, Integer exitCancel, Integer vacancy, Vehicle vehicle) {
        this.codeRegistration = codeRegistration;
        this.entranceCancel = entranceCancel;
        this.exitCancel = exitCancel;
        this.vacancy = vacancy;
        this.vehicle = vehicle;
    }

    public Integer getCodeRegistration() {
        return codeRegistration;
    }

    public void setCodeRegistration(Integer codeRegistration) {
        this.codeRegistration = codeRegistration;
    }

    public Integer getEntranceCancel() {
        return entranceCancel;
    }

    public void setEntranceCancel(Integer entranceCancel) {
        this.entranceCancel = entranceCancel;
    }

    public Integer getExitCancel() {
        return exitCancel;
    }

    public void setExitCancel(Integer exitCancel) {
        this.exitCancel = exitCancel;
    }

    public Integer getVacancy() {
        return vacancy;
    }

    public void setVacancy(Integer vacancy) {
        this.vacancy = vacancy;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public Boolean checkAuthorization(Vehicle vehicle) {
        return null;
    }

    @Override
    public Boolean generateTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Boolean releaseCancel(Vehicle vehicle) {
        return null;
    }
}
