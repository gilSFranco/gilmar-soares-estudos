package com.compasso.parking_management_spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.parking_management_spring_boot.entities.ParkingSpot;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long>{
    List<ParkingSpot> findByLicensePlate(String licensePlate);
}