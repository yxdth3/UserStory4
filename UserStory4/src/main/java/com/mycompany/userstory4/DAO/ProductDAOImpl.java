/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.DAO;

import com.mycompany.userstory4.Domain.Product;
import com.mycompany.userstory4.Exception.PersistenceEXception;
import com.mycompany.userstory4.Util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete DAO implementation using JDBC and PreparedStatement.
 * Uses try-with-resources to auto-close DB resources.
 * Translates SQLException into PersistenceException.
 */

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void addProduct(Product product) throws PersistenceEXception {
        String sql = "INSERT INTO products(name,price,stock) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
           
            ps.setString(1, product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setInt(3, product.getStock());
            int affected = ps.executeUpdate();
            
            if (affected == 0) {
                throw new PersistenceEXception("Insert failed, no rows affected.");
            }
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            // Wrap SQLException to avoid exposing details to upper layers
            throw new PersistenceEXception("Error while inserting product",e);
        }
    }

    @Override
    public List<Product> getAllProducts() throws PersistenceEXception {
        String sql = "SELECT id, name, price, stock FROM products";
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            throw new PersistenceEXception("Error while retrieving products", e);
        }
        return list;
    }

    @Override
    public Product findByName(String name) throws PersistenceEXception {
        String sql = "SELECT id, name, price, stock FROM products WHERE name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("stock")
                    );
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new PersistenceEXception("Error while finding product by name", e);
        }
    }

    @Override
    public void updatePrice(int productId, double newPrice) throws PersistenceEXception {
        String sql = "UPDATE products SET price = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newPrice);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceEXception("Error while updating product price", e);
        }
    }

    @Override
    public void updateStock(int productId, int newStock) throws PersistenceEXception {
        String sql = "UPDATE products SET stock = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newStock);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceEXception("Error while updating product stock", e);
        }
    }

    @Override
    public void deleteById(int productId) throws PersistenceEXception {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceEXception("Error while deleting product", e);
        }
    }

    @Override
    public void deleteByName(String name) throws PersistenceEXception {
        String sql = "DELETE FROM products WHERE name = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceEXception("Error while deleting product",e);
        }
    }
    
}
