/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.exceptions;

import de.zray.coretex.Variable;

/**
 *
 * @author Vortex Acherontic
 */
public class InvalidVarTypeException extends Exception{
    public InvalidVarTypeException(Variable.Type real){
        super("Value need to be of type "+real.toString());
    }
}
