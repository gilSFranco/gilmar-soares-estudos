package model.entities;

public class Ticket implements Registration {
    private Integer codeRegistration;
    private Integer entranceCancel;
    private Integer exitCancel;
    private Integer vacancy;
    private Detached detached;
    private Parking parking;

    public Ticket() {
    }

    public Ticket(Integer codeRegistration, Integer entranceCancel, Integer exitCancel, Integer vacancy, Detached detached) {
        this.codeRegistration = codeRegistration;
        this.entranceCancel = entranceCancel;
        this.exitCancel = exitCancel;
        this.vacancy = vacancy;
        this.detached = detached;
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

    public Detached getDetached() {
        return detached;
    }

    public void setDetached(Detached detached) {
        this.detached = detached;
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
