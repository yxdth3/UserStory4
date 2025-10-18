/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.Exception;


/**
 * Thrown when trying to create a product with a name that already exists.
 */

public class DuplicateException extends Exception{
    public DuplicateException(String message){
        super (message);
    }
}
