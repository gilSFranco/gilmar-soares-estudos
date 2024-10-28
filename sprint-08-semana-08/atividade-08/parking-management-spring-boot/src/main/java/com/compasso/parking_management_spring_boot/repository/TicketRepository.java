package com.compasso.parking_management_spring_boot.repository;

import com.compasso.parking_management_spring_boot.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByVehicleLicensePlate(String plate);
}
