package model.entities;

public interface Registration {
    Boolean checkAuthorization(Vehicle vehicle);
    Boolean generateTicket(Ticket ticket);
    Boolean releaseCancel(Vehicle vehicle);
}
