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
 * @author vortex
 */
public class HelpDefinition extends CommandDefinition{
    
    public HelpDefinition() {
        super("help", "Shows the help menue");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set);
        
        ParameterSetDefinition set2 = new ParameterSetDefinition();
        set2.addParameterType(new ParameterType(ParameterType.Type.EMPTY));
        addParameterSetDefinition(set2);
        addParameterSetDefinition(set2);
    }
    
}
