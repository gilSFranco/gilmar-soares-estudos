package com.compasso.parking_management_spring_boot.service;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.exception.CategoryNotValidException;
import com.compasso.parking_management_spring_boot.exception.EntityNotFoundException;
import com.compasso.parking_management_spring_boot.exception.PlateUniqueViolationException;
import com.compasso.parking_management_spring_boot.repository.VehicleRepository;
import com.compasso.parking_management_spring_boot.validation.VehicleValidation;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.CreateVehicleDTO;
import com.compasso.parking_management_spring_boot.web.dto.mapper.VehicleMapper;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.ResponseUpdatePaymentDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.UpdatePaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;


    @Transactional
    public Vehicle registerVehicle(CreateVehicleDTO vehicleDTO) {

        VehicleValidation.validateEnumsVehicle(vehicleDTO);

        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDTO);

        String licensePlate = vehicleDTO.getLicensePlate();
        Optional<Vehicle> vehicleList = vehicleRepository.findByLicensePlate(licensePlate);
        if (vehicleList.isPresent()) {  // verifica existencia no banco para atualizar registro
            Vehicle existingVehicle = vehicleList.get();

            if (existingVehicle.equals(vehicle)) {

                existingVehicle.setRegister(true);

                return vehicleRepository.save(existingVehicle);
            } else {
                throw new PlateUniqueViolationException(String.format("License plate '%s' already registered in the system.", vehicle.getLicensePlate()));
            }
        } else {  // se não existir, realiza o cadastro
            if (vehicle.getCategory() == Category.MONTHLY) {
                vehicle.setPaymentDate(LocalDateTime.now());
            }
        }
        vehicle.setRegister(true);
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Optional<Vehicle> findByPlate(String plate) {

        return vehicleRepository.findByLicensePlate(plate);
    }

    @Transactional
    public void delete(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Vehicle by id '%s' not found..", id))
        );

        vehicle.setRegister(false);

        vehicleRepository.save(vehicle);

    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle register(Vehicle v) {
        return vehicleRepository.save(v);
    }

    @Transactional
    public ResponseUpdatePaymentDTO updateVehicle(UpdatePaymentDTO dto) {

        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(dto.getLicensePlate());

        ResponseUpdatePaymentDTO response = new ResponseUpdatePaymentDTO();

        if (vehicle.isPresent()) {  // checks existence in the bank to update the record
            Vehicle vehicleToUpdate = vehicle.get();
            if (vehicleToUpdate.getCategory() != Category.MONTHLY) {
                throw new CategoryNotValidException("To update payment date the vehicle must be MONTHLY category");
            } else {
                vehicleToUpdate.setPaymentDate(LocalDateTime.now());

                vehicleRepository.save(vehicleToUpdate);

                response.setLicensePlate(vehicleToUpdate.getLicensePlate());
                response.setTypeVehicle(vehicleToUpdate.getType());
                response.setPaymentDate(vehicleToUpdate.getPaymentDate());
                return response;
            }
        } else {
            throw new PlateUniqueViolationException(String.format("License plate '%s' not found in the database.", dto.getLicensePlate()));
        }
    }
}


