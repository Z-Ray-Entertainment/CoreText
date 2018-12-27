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
public class AliasDefinition extends CommandDefinition{
    
    public AliasDefinition() {
        super("alias", "Sets an alias to a given and exsiting command, if not used elsewhere.");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set);
    }
    
}
