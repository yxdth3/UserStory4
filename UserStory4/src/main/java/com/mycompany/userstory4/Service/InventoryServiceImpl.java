/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.Service;

import com.mycompany.userstory4.DAO.ProductDAO;
import com.mycompany.userstory4.DAO.ProductDAOImpl;
import com.mycompany.userstory4.Domain.Product;
import com.mycompany.userstory4.Exception.DuplicateException;
import com.mycompany.userstory4.Exception.InvalidDataException;
import com.mycompany.userstory4.Exception.PersistenceEXception;
import java.util.List;

/**
 * Implementation of business rules and validations.
 * This layer should not know about UI details or SQL.
 */

public class InventoryServiceImpl implements InventoryServiceLocal {
    private final ProductDAO productDAO = new ProductDAOImpl();
    
    @Override
    public void addProduct(Product product) throws InvalidDataException, DuplicateException, PersistenceEXception {
        validateProductData(product);

        // Check duplicate by name
        Product existing = productDAO.findByName(product.getName());
        if (existing != null) {
            throw new DuplicateException("Product with name '" + product.getName() + "' already exists.");
        }

        productDAO.addProduct(product);
    }

    @Override
    public List<Product> listProducts() throws PersistenceEXception {
        return productDAO.getAllProducts();
    }

    @Override
    public void updatePrice(int productId, double newPrice) throws InvalidDataException, PersistenceEXception {
        if (newPrice <= 0) {
            throw new InvalidDataException("Price must be greater than zero.");
        }
        productDAO.updatePrice(productId, newPrice);
    }

    @Override
    public void updateStock(int productId, int newStock) throws InvalidDataException, PersistenceEXception {
        if (newStock < 0) {
            throw new InvalidDataException("Stock cannot be negative.");
        }
        productDAO.updateStock(productId, newStock);
    }

    @Override
    public void deleteProduct(int productId) throws PersistenceEXception {
        productDAO.deleteById(productId);
    }

    @Override
    public Product findByName(String name) throws PersistenceEXception {
        return productDAO.findByName(name);
    }

    // Private helper for validations of creating product
    private void validateProductData(Product product) throws InvalidDataException {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new InvalidDataException("Product name cannot be empty.");
        }
        if (product.getPrice() <= 0) {
            throw new InvalidDataException("Product price must be greater than zero.");
        }
        if (product.getStock() < 0) {
            throw new InvalidDataException("Product stock cannot be negative.");
        }
    }
}
