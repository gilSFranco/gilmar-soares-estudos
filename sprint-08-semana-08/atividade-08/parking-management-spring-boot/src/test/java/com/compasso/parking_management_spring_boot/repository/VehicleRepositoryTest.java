package com.compasso.parking_management_spring_boot.repository;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import com.compasso.parking_management_spring_boot.web.dto.mapper.VehicleMapper;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.CreateVehicleDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.UpdatePaymentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void createVehicle_WithValidData_ReturnsVehicle() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO(
                "PASSENGERCAR",
                "MONTHLY",
                "ABC-1234"
        );

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);
        Vehicle newVehicle = vehicleRepository.save(vehicle);

        Vehicle sut = entityManager.find(Vehicle.class, newVehicle.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getType()).isEqualTo(TypeVehicle.valueOf(vehicleDTO.getType()));
        assertThat(sut.getCategory()).isEqualTo(Category.valueOf(vehicleDTO.getCategory()));
        assertThat(sut.getLicensePlate()).isEqualTo(vehicleDTO.getLicensePlate());
    }

    @Test
    void createVehicle_WithInvalidData_ThrowsException() {
        CreateVehicleDTO emptyVehicleDTO = new CreateVehicleDTO();
        CreateVehicleDTO invalidVehicleDTO = new CreateVehicleDTO(
                "",
                "",
                ""
        );

        Vehicle emptyVehicle = VehicleMapper.toVehicle(emptyVehicleDTO);
        Vehicle invalidVehicle = VehicleMapper.toVehicle(invalidVehicleDTO);

        assertThatThrownBy(() -> vehicleRepository.save(emptyVehicle)).isInstanceOf(DataIntegrityViolationException.class);
        assertThatThrownBy(() -> vehicleRepository.save(invalidVehicle)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void createVehicle_WithExistingPlate_ThrowsException() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO(
                "PASSENGERCAR",
                "MONTHLY",
                "ABC-1234"
        );

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);
        Vehicle newVehicle = entityManager.persistFlushFind(vehicle);
        entityManager.detach(newVehicle);
        newVehicle.setId(null);

        assertThatThrownBy(() -> vehicleRepository.save(newVehicle)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void getVehicle_ByExistingId_ReturnsVehicle() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO(
                "PASSENGERCAR",
                "MONTHLY",
                "ABC-1234"
        );

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);
        Vehicle newVehicle = entityManager.persistFlushFind(vehicle);

        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(newVehicle.getId());

        assertThat(vehicleOptional).isNotEmpty();
        assertThat(vehicleOptional.get()).isEqualTo(newVehicle);
    }

    @Test
    void getVehicle_ByUnexistingId_ReturnsEmpty() {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(1L);

        assertThat(vehicleOptional).isEmpty();
    }

    @Test
    void getVehicle_ByExistingPlate_ReturnsVehicle() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO(
                "PASSENGERCAR",
                "MONTHLY",
                "ABC-1234"
        );

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);
        Vehicle newVehicle = entityManager.persistFlushFind(vehicle);

        Optional<Vehicle> vehicleOptional = vehicleRepository.findByLicensePlate(newVehicle.getLicensePlate());

        assertThat(vehicleOptional).isNotEmpty();
        assertThat(vehicleOptional.get()).isEqualTo(newVehicle);
    }

    @Test
    void getVehicle_ByUnexistingPlate_ReturnsEmpty() {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByLicensePlate("ABC-1234");

        assertThat(vehicleOptional).isEmpty();
    }

    @Sql(scripts = "/vehicles/import_vehicles.sql")
    @Test
    void listVehicles_ReturnsAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        assertThat(vehicles).isNotEmpty();
        assertThat(vehicles).hasSize(6);
    }

    @Test
    void listVehicles_ReturnsNoVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        assertThat(vehicles).isEmpty();
    }

    @Test
    void updateVehicles_WithExistingId_SetRegisterFalse() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO(
                "PASSENGERCAR",
                "MONTHLY",
                "ABC-1234"
        );

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);
        Vehicle newVehicle = entityManager.persistFlushFind(vehicle);

        newVehicle.setRegister(false);
        vehicleRepository.save(newVehicle);

        Vehicle removedVehicle = entityManager.find(Vehicle.class, newVehicle.getId());
        assertThat(removedVehicle).isNotNull();
        assertThat(removedVehicle.getRegister()).isFalse();
    }

    @Test
    void updateVehicles_WithExistingPlate_SetRegisterFalse() {
        CreateVehicleDTO vehicleDTO = new CreateVehicleDTO(
                "PASSENGERCAR",
                "MONTHLY",
                "ABC-1234"
        );

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);
        entityManager.persistFlushFind(vehicle);

        UpdatePaymentDTO paymentDTO = new UpdatePaymentDTO(
                "ABC-1234"
        );

        Optional<Vehicle> vehicleOptional = vehicleRepository.findByLicensePlate(paymentDTO.getLicensePlate());
        Vehicle vehicleToUpdate = vehicleOptional.get();

        vehicleToUpdate.setPaymentDate(LocalDateTime.now());
        vehicleRepository.save(vehicleToUpdate);

        assertThat(vehicleToUpdate).isNotNull();
        assertThat(vehicleToUpdate.getPaymentDate()).isNotNull();
    }
}
