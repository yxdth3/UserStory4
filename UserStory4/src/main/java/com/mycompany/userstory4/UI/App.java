/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.userstory4.UI;

import com.mycompany.userstory4.Domain.Product;
import com.mycompany.userstory4.Exception.DuplicateException;
import com.mycompany.userstory4.Exception.InvalidDataException;
import com.mycompany.userstory4.Exception.PersistenceEXception;
import com.mycompany.userstory4.Service.InventoryServiceImpl;
import com.mycompany.userstory4.Service.InventoryServiceLocal;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Main UI class. Uses JOptionPane for simple menu.
 * UI interacts only with the Service layer.
 * Catches exceptions from layers below and shows friendly messages.
 *
 * This is intentionally written in a simple way appropriate for a junior dev.
 */

public class App {

   private static InventoryServiceLocal service = new InventoryServiceImpl();

    // Counters of successful operations
    private static int createdCount = 0;
    private static int updatedCount = 0;
    private static int deletedCount = 0;

    public static void main(String[] args) throws PersistenceEXception {
        // Simple loop menu using JOptionPane
        boolean exit = false;
        while (!exit) {
            String menu = """
                          Mini-Store - choose an option:
                          1. Add product
                          2. List inventory
                          3. Update price
                          4. Update stock
                          5. Delete product
                          6. Find product by name
                          7. Exit and show summary""";

            String choice = JOptionPane.showInputDialog(null, menu, "Main Menu", JOptionPane.QUESTION_MESSAGE);
            if (choice == null) { // user pressed cancel
                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    exit = true;
                }
                continue;
            }

            switch (choice.trim()) {
                case "1" -> addProduct();
                case "2" -> listInventory();
                case "3" -> updatePrice();
                case "4" -> updateStock();
                case "5" -> deleteProduct();
                case "6" -> findProduct();
                case "7" -> {
                    exit = true;
                    showSummary();
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please choose a number from 1 to 7.");
            }
        }
    }

    private static void addProduct() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter product name:");
            if (name == null) return; // canceled

            String priceStr = JOptionPane.showInputDialog(null, "Enter product price:");
            if (priceStr == null) return;

            String stockStr = JOptionPane.showInputDialog(null, "Enter product stock:");
            if (stockStr == null) return;

            double price;
            int stock;
            try {
                price = Double.parseDouble(priceStr);
                stock = Integer.parseInt(stockStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price or stock is not in correct numeric format.");
                return;
            }

            Product p = new Product(null, name.trim(), price, stock);
            service.addProduct(p);
            createdCount++;
            JOptionPane.showMessageDialog(null, "Product added successfully: " + p);

        } catch (InvalidDataException | DuplicateException ex) {
            JOptionPane.showMessageDialog(null, "Validation error: " + ex.getMessage());
        } catch (PersistenceEXception ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage());
        }
    }

    private static void listInventory() {
        try {
            List<Product> list = service.listProducts();
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Inventory is empty.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Product p : list) {
                    sb.append(p.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PersistenceEXception ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    private static void updatePrice() throws PersistenceEXception {
        try {
            String idStr = JOptionPane.showInputDialog(null, "Enter product id to update price:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            String priceStr = JOptionPane.showInputDialog(null, "Enter new price:");
            if (priceStr == null) return;
            double newPrice = Double.parseDouble(priceStr);

            service.updatePrice(id, newPrice);
            updatedCount++;
            JOptionPane.showMessageDialog(null, "Price updated successfully.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid numeric input.");
        } catch (InvalidDataException ex) {
            JOptionPane.showMessageDialog(null, "Validation error: " + ex.getMessage());
        } catch (PersistenceEXception ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    private static void updateStock() throws PersistenceEXception {
        try {
            String idStr = JOptionPane.showInputDialog(null, "Enter product id to update stock:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            String stockStr = JOptionPane.showInputDialog(null, "Enter new stock:");
            if (stockStr == null) return;
            int newStock = Integer.parseInt(stockStr);

            service.updateStock(id, newStock);
            updatedCount++;
            JOptionPane.showMessageDialog(null, "Stock updated successfully.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid numeric input.");
        } catch (InvalidDataException ex) {
            JOptionPane.showMessageDialog(null, "Validation error: " + ex.getMessage());
        } catch (PersistenceEXception ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    private static void deleteProduct() throws PersistenceEXception {
        try {
            String idStr = JOptionPane.showInputDialog(null, "Enter product id to delete:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            service.deleteProduct(id);
            deletedCount++;
            JOptionPane.showMessageDialog(null, "Product deleted successfully.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid numeric input.");
        } catch (PersistenceEXception ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    private static void findProduct() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter product name to search:");
            if (name == null) return;
            Product p = service.findByName(name.trim());
            if (p == null) {
                JOptionPane.showMessageDialog(null, "Product not found.");
            } else {
                JOptionPane.showMessageDialog(null, p.toString(), "Product found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PersistenceEXception ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    private static void showSummary() {
        String msg = """
                     Summary of operations:
                     Products created: """ + createdCount + "\n"
                + "Products updated: " + updatedCount + "\n"
                + "Products deleted: " + deletedCount;
        JOptionPane.showMessageDialog(null, msg, "Summary", JOptionPane.INFORMATION_MESSAGE);
    }
}
