package com.compasso.parking_management_spring_boot.service;

import com.compasso.parking_management_spring_boot.entities.Ticket;
import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> findAll(String plate) {
        if (plate.isEmpty()) {
            return ticketRepository.findAll();
        }
        return ticketRepository.findByVehicleLicensePlate(plate);
    }

    public Ticket register(Vehicle v, String s,Integer in){
        Ticket t = new Ticket(v,LocalDateTime.now(),in,s);
        
        return ticketRepository.save(t);
    }

    public Optional<Ticket> findById(Long id){
        return ticketRepository.findById(id);
    } 
    
    public Double charge(Optional<Ticket> t) {
        LocalDateTime rightNow = LocalDateTime.now();
        Duration duration = Duration.between(t.get().getEntryDate(), rightNow);
        long min = duration.toMinutes(); 
        double charge = min * 0.10;
        if (charge < 5){
            charge = 5;
        }
        return charge;
    }

    public void update(Ticket t){
      ticketRepository.save(t);
    }
    
}
