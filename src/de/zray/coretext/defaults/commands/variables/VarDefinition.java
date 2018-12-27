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
public class VarDefinition extends CommandDefinition{
    
    public VarDefinition() {
        super("var", "Creates a new variable with the given type(1) and name(2)");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));//type
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));//name
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));//value
        addParameterSetDefinition(set);
        
        ParameterSetDefinition set2 = new ParameterSetDefinition();
        set2.addParameterType(new ParameterType(ParameterType.Type.STRING));//subcomand
        set2.addParameterType(new ParameterType(ParameterType.Type.STRING));//variable
        addParameterSetDefinition(set2);
    }
    
}
