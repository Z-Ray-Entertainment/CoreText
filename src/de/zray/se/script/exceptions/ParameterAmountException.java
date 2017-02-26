/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.exceptions;

/**
 *
 * @author Vortex Acherontic
 */
public class ParameterAmountException extends Exception{

    public ParameterAmountException(int amountNeeded, int amountGiven, String parameters) {
            super("Invalid amount of parameters, "+amountNeeded+" needed and "+amountGiven+" given. "+parameters);
    }
    
}
