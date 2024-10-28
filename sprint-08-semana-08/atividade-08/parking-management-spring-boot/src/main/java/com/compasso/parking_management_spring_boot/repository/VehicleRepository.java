package com.compasso.parking_management_spring_boot.repository;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findById(Long id);
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}

