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
public class EchoDefinition extends CommandDefinition{
    
    public EchoDefinition() {
        super("echo", "Simply returns the value of the first an only parameter,\n"
                + "such as return values from clips and or functions executed\n"
                + "with in the echo");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set);
        
        ParameterSetDefinition set2 = new ParameterSetDefinition();
        set2.addParameterType(new ParameterType(ParameterType.Type.INFINITE));
        addParameterSetDefinition(set2);
    }
    
}
