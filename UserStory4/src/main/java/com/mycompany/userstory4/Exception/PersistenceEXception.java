/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userstory4.Exception;

/**
 * Wraps SQLExceptions or other persistence-layer exceptions so that
 * the UI layer doesn't get database internals.
 */

public class PersistenceEXception extends Exception{
    public PersistenceEXception(String message, Throwable cause){
        super(message, cause);
    }
    
    public PersistenceEXception(String message) {
        super (message);
    }
}
