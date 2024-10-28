package com.compasso.parking_management_spring_boot.service;

import com.compasso.parking_management_spring_boot.entities.Ticket;
import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TicketService.class)
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepository;

    @Test
    void findAll_plateIsEmpty_returnsAllTickets() {
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll("");

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void findAll_plateIsNotEmpty_returnsTicketsForSpecificVehicle() {
        String plate = "ABC-1234";
        List<Ticket> tickets = List.of(new Ticket());
        when(ticketRepository.findByVehicleLicensePlate(plate)).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll(plate);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        verify(ticketRepository, times(1)).findByVehicleLicensePlate(plate);
    }

    @Test
    void register_vehicleIsValid_ticketCreated() {
        Vehicle vehicle = new Vehicle();
        Integer gate = 1;
        Ticket ticket = new Ticket(vehicle, LocalDateTime.now(), gate, "ABC-1234");
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket result = ticketService.register(vehicle, "ABC-1234", gate);

        assertThat(result).isNotNull();
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void findById_idExists_returnsTicket() {
        Long id = 1L;
        Ticket ticket = new Ticket();
        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));

        Optional<Ticket> result = ticketService.findById(id);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(ticket);
        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    void findById_idDoesNotExist_returnsEmpty() {
        Long id = 1L;
        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Ticket> result = ticketService.findById(id);

        assertThat(result).isEmpty();
        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    void charge_ticketDurationLessThanMinimum_setsMinimumCharge() {
        Ticket ticket = new Ticket();
        ticket.setEntryDate(LocalDateTime.now().minusMinutes(2));
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));

        Double charge = ticketService.charge(Optional.of(ticket));

        assertThat(charge).isEqualTo(5.0);
    }

    @Test
    void update_ticketExists_ticketUpdatedSuccessfully() {
        Ticket ticket = new Ticket();
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        ticketService.update(ticket);

        verify(ticketRepository, times(1)).save(ticket);
    }

}
