/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ministore?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwe.123*";

    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
    
    //public static Connection getConnection() throws SQLException {
    //    try {
    //        Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
    //        return DriverManager.getConnection(URL, USER, PASSWORD);
    //    } catch (ClassNotFoundException e) {
    //        throw new SQLException("MySQL Driver not found!", e);
    //    }
}
