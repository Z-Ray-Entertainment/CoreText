/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands.bool;

import de.zray.coretext.command.CommandDefinition;
import de.zray.coretext.command.ParameterSetDefinition;
import de.zray.coretext.command.ParameterType;

/**
 *
 * @author vortex
 */
public class IfDefinition extends CommandDefinition{
    
    public IfDefinition() {
        super("if", "The standart boolean comperator");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.BOOLEAN));
        set.addParameterType(new ParameterType(ParameterType.Type.CODEBLOCK));
        set.addParameterType(new ParameterType(ParameterType.Type.CODEBLOCK));
        addParameterSetDefinition(set);
    }
    
}
