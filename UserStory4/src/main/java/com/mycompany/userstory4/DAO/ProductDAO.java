/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.DAO;

import com.mycompany.userstory4.Domain.Product;
import com.mycompany.userstory4.Exception.PersistenceEXception;
import java.util.List;

/**
 * DAO interface for product CRUD operations.
 * The Service layer depends on this interface (not on implementation).
 */

public interface ProductDAO {
    void addProduct(Product product) throws PersistenceEXception;
    List<Product> getAllProducts() throws PersistenceEXception;
    Product findByName(String name) throws PersistenceEXception;
    void updatePrice(int productId, double newPrice) throws PersistenceEXception;
    void updateStock(int productId, int newStock) throws PersistenceEXception;
    void deleteById(int productId) throws PersistenceEXception;
    void deleteByName(String name) throws PersistenceEXception;
}
