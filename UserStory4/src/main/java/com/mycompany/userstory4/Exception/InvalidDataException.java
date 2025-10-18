/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.Exception;


/**
 * Thrown when the input data for a product is invalid.
 * Examples: empty name, price <= 0, negative stock.
 */

public class InvalidDataException extends Exception{
    public InvalidDataException(String message) {
        super(message); 
    }
}
