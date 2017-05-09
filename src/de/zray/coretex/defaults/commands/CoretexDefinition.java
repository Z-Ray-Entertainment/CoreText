/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands;

import de.zray.coretex.command.CommandDefinition;
import de.zray.coretex.command.ParameterSetDefinition;
import de.zray.coretex.command.ParameterType;

/**
 *
 * @author Vortex Acherontic
 */
public class CoretexDefinition extends CommandDefinition{
    public CoretexDefinition() {
        super("coretx", "This command is only fore coretex, and returns some values,\n"
                + "such as Version, and can also be used to do some minor configurations.");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set);
    }
    
}
