package com.compasso.parking_management_spring_boot.web.controller;

import com.compasso.parking_management_spring_boot.entities.Ticket;
import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import com.compasso.parking_management_spring_boot.service.ParkingSpotService;
import com.compasso.parking_management_spring_boot.service.TicketService;
import com.compasso.parking_management_spring_boot.service.VehicleService;
import com.compasso.parking_management_spring_boot.web.dto.ticket.OutDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private ParkingSpotService parkingSpotService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLeaveVehicle_success() throws Exception {
        Long ticketId = 1L;
        OutDTO out = new OutDTO(2);
        String jsonContent = objectMapper.writeValueAsString(out);

        Ticket ticket = new Ticket();
        Vehicle vehicle = new Vehicle();
        vehicle.setCategory(Category.ONETIME);
        ticket.setVehicle(vehicle);

        when(ticketService.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(parkingSpotService.gateOutVerification(any(Integer.class), any(Vehicle.class))).thenReturn(true);
        when(ticketService.charge(any())).thenReturn(10.0);

        mockMvc.perform(post("/api/v1/tickets/{id}/leave", ticketId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void testLeaveVehicle_ticketNotFound() throws Exception {
        Long ticketId = 1L;
        OutDTO out = new OutDTO(2);
        String jsonContent = objectMapper.writeValueAsString(out);

        when(ticketService.findById(ticketId)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/tickets/{id}/leave", ticketId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void testLeaveVehicle_badRequest() throws Exception {
        Long ticketId = 1L;
        OutDTO out = new OutDTO(2);
        String jsonContent = objectMapper.writeValueAsString(out);

        Ticket ticket = new Ticket();
        ticket.setVehicle(new Vehicle());

        when(ticketService.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(parkingSpotService.gateOutVerification(any(Integer.class), any(Vehicle.class))).thenReturn(false);

        mockMvc.perform(post("/api/v1/tickets/{id}/leave", ticketId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGateInVerification() {
        Vehicle deliveryTruck = new Vehicle();
        deliveryTruck.setType(TypeVehicle.DELIVERYTRUCK);

        assertFalse(parkingSpotService.gateInVerification(4, deliveryTruck));
    }

    @Test
    void testGateOutVerification() {
        Vehicle deliveryTruck = new Vehicle();
        deliveryTruck.setType(TypeVehicle.MOTORCYCLE);

        assertFalse(parkingSpotService.gateInVerification(7, deliveryTruck));
    }
}
