package model.dao;

import model.entities.Vehicle;

import java.util.List;

public interface VehicleDao {
    void insert(Vehicle vehicle);
    void update(Vehicle vehicle);
    void deleteByPlate(String plate);
    Vehicle findByPlate(String plate);
    Vehicle findByCategoryId(Integer id);
    Vehicle findByMonthlyPayerId(Integer id);
    List<Vehicle> findAll();
}
