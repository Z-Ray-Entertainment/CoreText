/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.exceptions;

import de.zray.se.script.AbstractCommand;

/**
 *
 * @author Vortex Acherontic
 */
public class DublicateCommandException extends Exception{

    public DublicateCommandException(AbstractCommand cmd) {
        super(cmd.toString()+" already exist in command list.");
    }
    
}
