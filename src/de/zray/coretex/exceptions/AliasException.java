/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.exceptions;

/**
 *
 * @author Vortex Acherontic
 */
public class AliasException extends Exception{

    public AliasException(String alias) {
        super("The alias: "+alias+" already exist.");
    }
}
