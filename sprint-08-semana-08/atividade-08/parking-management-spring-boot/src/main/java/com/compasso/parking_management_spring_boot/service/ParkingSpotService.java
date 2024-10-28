package com.compasso.parking_management_spring_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.compasso.parking_management_spring_boot.exception.IvalidCancelException;
import org.springframework.stereotype.Service;

import com.compasso.parking_management_spring_boot.entities.ParkingSpot;
import com.compasso.parking_management_spring_boot.entities.Ticket;
import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import com.compasso.parking_management_spring_boot.repository.ParkingSpotRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ParkingSpotService {

    private final ParkingSpotRepository pr;

    public int getAllMonthlySpots(){
        List<ParkingSpot> list = pr.findAll();
        return (int) list.stream()
                .filter(ParkingSpot::getMonthly)
                .count();
    }

    public int getAllOneTimeSpots(){
        List<ParkingSpot> list = pr.findAll();
        return (int) list.stream()
                .filter(p -> !p.getMonthly())
                .count();
    }

    public int getAllMonthlySpotsOccupied(){
        List<ParkingSpot> list = pr.findAll();
        return (int) list.stream()
                .filter(p -> p.getMonthly() && p.getOccupied())
                .count();
    }

    public int getAllOneTimeSpotsOccupied(){
        List<ParkingSpot> list = pr.findAll();
        return (int) list.stream()
                .filter(p -> !p.getMonthly() && p.getOccupied())
                .count();
    }

    public List<ParkingSpot> getAvalibleSpotM(){
        List<ParkingSpot> list = pr.findAll();
        return list.stream().filter(p -> p.getMonthly() && !p.getOccupied()).collect(Collectors.toList());

    }

    public List<ParkingSpot> getAvalibleSpotO(){
        List<ParkingSpot> list = pr.findAll();
        return list.stream().filter(p -> !p.getMonthly() && !p.getOccupied()).collect(Collectors.toList());

    }

    public List<ParkingSpot> findByPlate(String licensePlate) {
        return pr.findByLicensePlate(licensePlate);
    }

   /* public void updateMonthlyCapacity(int newTotal) {
        // Verifique se o novo total é válido
        if (newTotal < 0) {
            throw new IllegalArgumentException("A capacidade não pode ser negativa.");
        }

        // Atualize todas as vagas que são mensais
        List<ParkingSpot> monthlySpots = getAvalibleSpotM();
        for (ParkingSpot spot : monthlySpots) {
            spot.setOccupied(true);
        }
        pr.saveAll(monthlySpots); // Salva todas as vagas mensais atualizadas
    }*/

    public void register(ParkingSpot p){
        pr.save(p);
    }

    public List<ParkingSpot> spotsWanted(Vehicle v){
        int spotsRequired;
        if (v.getCategory() == Category.MONTHLY) {
            if (v.getType() == TypeVehicle.MOTORCYCLE) {
                spotsRequired = 1;
                return handleMotoM(v, spotsRequired);
            } else if (v.getType() == TypeVehicle.PASSENGERCAR) {
                spotsRequired = 2;
                return handleCarM(v, spotsRequired);
            }
        }
        if (v.getCategory() == Category.ONETIME){
            if (v.getType() == TypeVehicle.MOTORCYCLE) {
                spotsRequired = 1;
                return handleMotoOne(v, spotsRequired);
            } else if (v.getType() == TypeVehicle.PASSENGERCAR) {
                spotsRequired = 2;
                return handleCarOne(v, spotsRequired);
            } else if (v.getType() == TypeVehicle.DELIVERYTRUCK) {
                spotsRequired = 4;
                return handleTruck(v, spotsRequired);
            }
        }
        if (v.getCategory() == Category.DELIVERY) {
            spotsRequired = 4;
            return handleTruck(v, spotsRequired);
        }

        return new ArrayList<>();
    }

    private List<ParkingSpot> handleMotoOne(Vehicle v, int spotsRequired) {
        List<ParkingSpot> availableSpots = getAvalibleSpotO();
        if (availableSpots.size() >= spotsRequired) {
            markSpotsAsOccupied(availableSpots.subList(0, spotsRequired),v);
            return availableSpots.subList(0, spotsRequired);
        }
        return new ArrayList<>();
    }

    private List<ParkingSpot> handleCarOne(Vehicle v, int spotsRequired) {
        List<ParkingSpot> availableSpots = getAvalibleSpotO();
        if (availableSpots.size() >= spotsRequired) {
            markSpotsAsOccupied(availableSpots.subList(0, spotsRequired),v);
            return availableSpots.subList(0, spotsRequired);
        }
        return new ArrayList<>();
    }

    private List<ParkingSpot> handleVehicle(Vehicle v, int spotsRequired) {
        List<ParkingSpot> availableSpots = getAvalibleSpotM();

        if (availableSpots.size() >= spotsRequired) {
            markSpotsAsOccupied(availableSpots.subList(0, spotsRequired),v);
            return availableSpots.subList(0, spotsRequired);
        }

        List<ParkingSpot> allAvailableSpots = getAvalibleSpotO();
        if (allAvailableSpots.size() >= spotsRequired) {
            markSpotsAsOccupied(allAvailableSpots.subList(0, spotsRequired),v);
            return allAvailableSpots.subList(0, spotsRequired);
        }

        return new ArrayList<>();
    }

    private List<ParkingSpot> handleMotoM(Vehicle v, int spotsRequired) {
        return handleVehicle(v, spotsRequired);
    }

    private List<ParkingSpot> handleCarM(Vehicle v, int spotsRequired) {
        return handleVehicle(v, spotsRequired);
    }

    private List<ParkingSpot> handleTruck(Vehicle v, int spotsRequired) {
        List<ParkingSpot> availableSpots = getAvalibleSpotO();
        if (availableSpots.size() >= spotsRequired) {
            markSpotsAsOccupied(availableSpots.subList(0, spotsRequired),v);
            return availableSpots.subList(0, spotsRequired);
        }
        return new ArrayList<>();
    }

    @Transactional
    public void markSpotsAsOccupied(List<ParkingSpot> spots, Vehicle v) {
    if (spots != null) {
        for (ParkingSpot spot : spots) {
            spot.setOccupied(true);
            spot.setLicensePlate(v.getLicensePlate());            
            pr.save(spot); 
        }
        pr.flush(); 
    }
}

    @Transactional
    public void markSpotsAsUnoccupied(Ticket t) {
    List<ParkingSpot> spots = convertToList(t.getSpots());
    if (spots != null) {
        for (ParkingSpot spot : spots) {
            spot.setOccupied(false);
            spot.setLicensePlate(null);            
            pr.save(spot); 
        }
        pr.flush(); 
    }
}

    public void delete(ParkingSpot spotToRemove) {
        pr.delete(spotToRemove);
    }

    public String convert(List<ParkingSpot> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getId());
            if (i < list.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public List<ParkingSpot> convertToList(String ids) {
        List<ParkingSpot> list = new ArrayList<>();
        String[] idArray = ids.split(" ");
        
        for (String id : idArray) {
            Long parkingSpotId = Long.valueOf(id);
            list.add(pr.findById(parkingSpotId).get());
        }
        
        return list;
    }

    public Boolean gateInVerification(Integer io, Vehicle v){
        if (v.getType() == TypeVehicle.DELIVERYTRUCK && io != 1){
            throw new IvalidCancelException("Delivery truck can just entry by gate 1.");
        }
        else if (v.getType() == TypeVehicle.MOTORCYCLE && io != 5){
            throw new IvalidCancelException("Motorcycle can just entry by gate 5.");
        }
       
            return true;
    }

    public Boolean gateOutVerification(Integer io, Vehicle v){
        if (v.getType() == TypeVehicle.MOTORCYCLE && io != 10){
            throw new IvalidCancelException("Motorcycle can just exit by gate 10.");
        }
        return true;
    }

    public Boolean verifyExistingVacancies() {
        List<ParkingSpot> listPark = pr.findAll();
        return !listPark.isEmpty();
    }
    
}