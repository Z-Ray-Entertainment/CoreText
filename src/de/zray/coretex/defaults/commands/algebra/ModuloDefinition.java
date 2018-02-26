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
public class ModuloDefinition extends CommandDefinition{
    public ModuloDefinition() {
        super("mod", "returns the modulo of value a with vlaue b");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.DOUBLE));
        set.addParameterType(new ParameterType(ParameterType.Type.DOUBLE));
        addParameterSetDefinition(set);
    }
    
}
