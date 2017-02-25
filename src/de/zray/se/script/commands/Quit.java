/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands;

import de.zray.se.script.AbstractCommand;
import de.zray.se.script.Parameter;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Quit extends AbstractCommand{
    public Quit() {
        super("quit", null);
    }

    @Override
    public String action(List<Parameter> params) {
        System.exit(0);
        return "Quit";
    }
    
}
