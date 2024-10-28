package com.compasso.parking_management_spring_boot.web.controller;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import com.compasso.parking_management_spring_boot.service.VehicleService;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.CreateVehicleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateVehicle() throws Exception {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO("PASSENGERCAR", "MONTHLY", "ABC-1234");
        Vehicle vehicle = new Vehicle(1L, TypeVehicle.PASSENGERCAR, Category.MONTHLY, "ABC-1234", LocalDateTime.now(), true);

        when(vehicleService.registerVehicle(any(CreateVehicleDTO.class))).thenReturn(vehicle);
        mockMvc.perform(post("/api/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", equalTo("http://localhost/api/v1/vehicles/1")))
                .andExpect(jsonPath("$.licensePlate").value(vehicle.getLicensePlate()))
                .andExpect(jsonPath("$.type").value(vehicle.getType().toString()));
    }

    @Test
    void testGetAllVehicles() throws Exception {
        Vehicle vehicle1 = new Vehicle(1L, TypeVehicle.PASSENGERCAR, Category.MONTHLY, "ABC1234", LocalDateTime.now(), true);
        Vehicle vehicle2 = new Vehicle(2L, TypeVehicle.MOTORCYCLE, Category.ONETIME, "XYZ9876", null, true);
        List<Vehicle> vehicleList = Arrays.asList(vehicle1, vehicle2);

        when(vehicleService.findAll()).thenReturn(vehicleList);

        mockMvc.perform(get("/api/v1/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].licensePlate").value(vehicle1.getLicensePlate()))
                .andExpect(jsonPath("$[1].licensePlate").value(vehicle2.getLicensePlate()));
    }

    @Test
    void testGetVehicleByPlate() throws Exception {
        Vehicle vehicle = new Vehicle(1L, TypeVehicle.PASSENGERCAR, Category.MONTHLY, "ABC-1234", LocalDateTime.now(), true);

        when(vehicleService.findByPlate("ABC-1234")).thenReturn(Optional.of(vehicle));

        mockMvc.perform(get("/api/v1/vehicles").param("plate", "ABC-1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].licensePlate").value(vehicle.getLicensePlate()));
    }

    @Test
    void testDeleteVehicle() throws Exception {
        doNothing().when(vehicleService).delete(anyLong());

        mockMvc.perform(delete("/api/v1/vehicles/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetVehicleByInvalidPlate() throws Exception {
        when(vehicleService.findByPlate("XYZ-9876")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/vehicles").param("plate", "XYZ-9876"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}