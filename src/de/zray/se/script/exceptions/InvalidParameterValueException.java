/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.exceptions;

import de.zray.se.script.Parameter;

/**
 *
 * @author Vortex Acherontic
 */
public class InvalidParameterValueException extends Exception{

    public InvalidParameterValueException(Parameter.Type inputType, Parameter.Type setType) {
        super(inputType.toString()+" is invalid you must set a value as "+setType);
    }
    
    public InvalidParameterValueException(String msg) {
        super(msg);
    }
}
