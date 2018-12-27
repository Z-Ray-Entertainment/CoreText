/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands;

import de.zray.coretext.command.CommandDefinition;
import de.zray.coretext.command.ParameterSetDefinition;
import de.zray.coretext.command.ParameterType;

/**
 *
 * @author Vortex Acherontic
 */
public class CoretexDefinition extends CommandDefinition{
    public CoretexDefinition() {
        super("coretx", "This command is only for coretex, and returns some values,\n"
                + "such as Version, and can also be used to do some minor configurations.");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set);
    }
    
}
