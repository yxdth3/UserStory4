/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.Service;

import com.mycompany.userstory4.Domain.Product;
import com.mycompany.userstory4.Exception.DuplicateException;
import com.mycompany.userstory4.Exception.InvalidDataException;
import com.mycompany.userstory4.Exception.PersistenceEXception;
import java.util.List;

/**
 * Service interface describing business operations.
 * UI will call these methods (not DAO directly).
 */

public interface InventoryServiceLocal {
    void addProduct(Product product)throws InvalidDataException, DuplicateException, PersistenceEXception;
    List<Product> listProducts() throws PersistenceEXception;
    void updatePrice(int productId, double newPrice) throws InvalidDataException, PersistenceEXception;
    void updateStock(int productId, int newStock) throws InvalidDataException, PersistenceEXception;
    void deleteProduct(int productId) throws PersistenceEXception;
    Product findByName(String name) throws PersistenceEXception;
}
