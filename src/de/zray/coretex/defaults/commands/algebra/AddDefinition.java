/*
 * Copyright by Z-Ray Entertainment 2018
 * Unauthorized copying or distributing of this source is not allowed
 * for further informations contact: support@z-ray.de
 */
package de.zray.coretex.defaults.commands.algebra;

import de.zray.coretex.command.CommandDefinition;
import de.zray.coretex.command.ParameterSetDefinition;
import de.zray.coretex.command.ParameterType;

/**
 *
 * @author vortex
 */
public class AddDefinition extends CommandDefinition{
    
    public AddDefinition() {
        super("add", "Adds the following 2 and returns its value");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.DOUBLE));
        set.addParameterType(new ParameterType(ParameterType.Type.DOUBLE));
        addParameterSetDefinition(set);
        
        ParameterSetDefinition set2 = new ParameterSetDefinition();
        set2.addParameterType(new ParameterType(ParameterType.Type.STRING));
        set2.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set2);
    }
    
}
