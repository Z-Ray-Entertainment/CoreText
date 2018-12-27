/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands.variables;

import de.zray.coretext.command.CommandDefinition;
import de.zray.coretext.command.ParameterSetDefinition;
import de.zray.coretext.command.ParameterType;

/**
 *
 * @author Vortex Acherontic
 */
public class ParseVarValueDefinition extends CommandDefinition{
    
    public ParseVarValueDefinition() {
        super("$", "Parses the value to the corrosponding variable.");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set);
    }
    
}
