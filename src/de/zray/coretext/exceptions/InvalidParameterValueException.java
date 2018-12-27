/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.exceptions;

import de.zray.coretext.command.ParameterType.Type;


/**
 *
 * @author Vortex Acherontic
 */
public class InvalidParameterValueException extends Exception{

    public InvalidParameterValueException(Type inputType, Type setType) {
        super(inputType.toString()+" is invalid you must set a value as "+setType);
    }
    
    public InvalidParameterValueException(String msg) {
        super(msg);
    }
}
