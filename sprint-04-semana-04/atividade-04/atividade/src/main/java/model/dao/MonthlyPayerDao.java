package model.dao;

import model.entities.MonthlyPayer;

import java.util.List;

public interface MonthlyPayerDao {
    void insert(MonthlyPayer monthlyPayer);
    void update(MonthlyPayer monthlyPayer);
    void deleteById(Integer id);
    MonthlyPayer findById(Integer id);
    List<MonthlyPayer> findAll();
}
