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
public class SubstractDefinition extends CommandDefinition{
    
    public SubstractDefinition() {
        super("sub", "Substracts the 2nd vaue from the 1st value and returns the result");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.DOUBLE));
        set.addParameterType(new ParameterType(ParameterType.Type.DOUBLE));
        addParameterSetDefinition(set);
    }
    
}