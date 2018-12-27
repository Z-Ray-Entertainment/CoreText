/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.exceptions;

/**
 *
 * @author Vortex Acherontic
 */
public class InvalidTypeException extends Exception{
    public InvalidTypeException() {
        super("UNDEFINED is an unvalid type.\nYou must set it to STRING, FLOAT, DOUBLE or INTEGER.");
    }
    
    public InvalidTypeException(String msg) {
        super(msg);
    }
}
