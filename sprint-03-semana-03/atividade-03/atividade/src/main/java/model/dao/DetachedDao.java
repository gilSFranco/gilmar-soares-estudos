package model.dao;

import model.entities.Detached;

import java.util.List;

public interface DetachedDao {
    void insert(Detached detached);
    void update(Detached detached);
    void deleteByPlate(String plate);
    Detached findByPlate(String plate);
    List<Detached> findAll();
}
