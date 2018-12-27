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
public class UnknownVariableException extends Exception{
    public UnknownVariableException(String name){
        super(name+" is not declared.");
    }
}
