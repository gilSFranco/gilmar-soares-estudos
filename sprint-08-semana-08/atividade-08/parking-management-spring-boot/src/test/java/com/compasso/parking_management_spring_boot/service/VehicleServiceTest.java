package com.compasso.parking_management_spring_boot.service;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.exception.CategoryNotValidException;
import com.compasso.parking_management_spring_boot.exception.EntityNotFoundException;
import com.compasso.parking_management_spring_boot.exception.PlateUniqueViolationException;
import com.compasso.parking_management_spring_boot.repository.VehicleRepository;
import com.compasso.parking_management_spring_boot.web.dto.mapper.VehicleMapper;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.CreateVehicleDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.ResponseUpdatePaymentDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.UpdatePaymentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = VehicleService.class)
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    void registerVehicle_vehicleDoesNotExist_success() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO("PASSENGERCAR", "ONETIME", "XXX-0000");
        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);

        when(vehicleRepository.findByLicensePlate(anyString())).thenReturn(Optional.empty());
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        Vehicle result = vehicleService.registerVehicle(vehicleDTO);

        assertNotNull(result);
        assertEquals(vehicle.getLicensePlate(), result.getLicensePlate());
    }

    @Test
    void registerVehicle_vehicleExistsWithSamePlate_success() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO("PASSENGERCAR", "ONETIME", "XXX-0000");
        Vehicle existingVehicle = VehicleMapper.toVehicle(vehicleDTO);

        when(vehicleRepository.findByLicensePlate(anyString())).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(existingVehicle);

        Vehicle result = vehicleService.registerVehicle(vehicleDTO);

        assertTrue(result.getRegister());
        verify(vehicleRepository).save(existingVehicle);
    }

    @Test
    void registerVehicle_vehicleExistsWithDifferentPlate_throwsPlateUniqueViolationException() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO("PASSENGERCAR", "ONETIME", "XXX-0000");
        Vehicle differentVehicle = new Vehicle();

        when(vehicleRepository.findByLicensePlate(anyString())).thenReturn(Optional.of(differentVehicle));

        assertThrows(PlateUniqueViolationException.class, () -> vehicleService.registerVehicle(vehicleDTO));
    }

    @Test
    void findByPlate_plateExists_success() {
        String plate = "ABC-1234";
        Vehicle vehicle = new Vehicle();

        when(vehicleRepository.findByLicensePlate(plate)).thenReturn(Optional.of(vehicle));

        Optional<Vehicle> result = vehicleService.findByPlate(plate);

        assertTrue(result.isPresent());
        assertEquals(vehicle, result.get());
    }

    @Test
    void delete_vehicleExists_success() {
        Long id = 1L;
        Vehicle vehicle = new Vehicle();

        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));

        vehicleService.delete(id);

        verify(vehicleRepository).save(vehicle);
        assertFalse(vehicle.getRegister());
    }

    @Test
    void delete_vehicleDoesNotExist_throwsEntityNotFoundException() {
        Long id = 1L;

        when(vehicleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> vehicleService.delete(id));
    }

    @Test
    void findAll_vehiclesExist_returnsListOfVehicles() {
        List<Vehicle> vehicles = List.of(new Vehicle(), new Vehicle());

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<Vehicle> result = vehicleService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void updateVehicle_vehicleExistsAndCategoryIsMonthly_updatesPaymentDate() {
        UpdatePaymentDTO dto = new UpdatePaymentDTO("ABC-1234");
        Vehicle vehicle = new Vehicle();
        vehicle.setCategory(Category.MONTHLY);

        when(vehicleRepository.findByLicensePlate(anyString())).thenReturn(Optional.of(vehicle));

        ResponseUpdatePaymentDTO result = vehicleService.updateVehicle(dto);

        assertNotNull(result.getPaymentDate());
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    void updateVehicle_vehicleExistsAndCategoryIsNotMonthly_throwsCategoryNotValidException() {
        UpdatePaymentDTO dto = new UpdatePaymentDTO("XYZ-9876");
        Vehicle vehicle = new Vehicle();
        vehicle.setCategory(Category.DELIVERY);

        when(vehicleRepository.findByLicensePlate(anyString())).thenReturn(Optional.of(vehicle));

        assertThrows(CategoryNotValidException.class, () -> vehicleService.updateVehicle(dto));
    }

    @Test
    void updateVehicle_vehicleDoesNotExist_throwsPlateUniqueViolationException() {
        UpdatePaymentDTO dto = new UpdatePaymentDTO("ABC-1234");

        when(vehicleRepository.findByLicensePlate(anyString())).thenReturn(Optional.empty());

        assertThrows(PlateUniqueViolationException.class, () -> vehicleService.updateVehicle(dto));
    }

}
