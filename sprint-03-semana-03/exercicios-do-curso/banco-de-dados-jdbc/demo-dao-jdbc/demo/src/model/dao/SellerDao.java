package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller sellerDao);
    void update(Seller sellerDao);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();
}
