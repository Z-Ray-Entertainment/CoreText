/*
 * Copyright by Z-Ray Entertainment 2018
 * Unauthorized copying or distributing of this source is not allowed
 * for further informations contact: support@z-ray.de
 */
package de.zray.coretext.defaults.commands.bool;

import de.zray.coretext.command.CommandDefinition;
import de.zray.coretext.command.ParameterSetDefinition;
import de.zray.coretext.command.ParameterType;

/**
 *
 * @author vortex
 */
public class NotEqualsDefinition extends CommandDefinition{
    public NotEqualsDefinition(){
        super("!=", "Tells if two parameters are not equals, returns true if they are unequal and true if there are equals.");
        ParameterSetDefinition set = new ParameterSetDefinition();
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        set.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set);
    }
}
