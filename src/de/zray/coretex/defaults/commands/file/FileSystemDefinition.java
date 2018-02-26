/*
 * Copyright by Z-Ray Entertainment 2018
 * Unauthorized copying or distributing of this source is not allowed
 * for further informations contact: support@z-ray.de
 */
package de.zray.coretex.defaults.commands.file;

import de.zray.coretex.command.CommandDefinition;
import de.zray.coretex.command.ParameterSetDefinition;
import de.zray.coretex.command.ParameterType;

/**
 *
 * @author vortex
 */
public class FileSystemDefinition extends CommandDefinition{
    public FileSystemDefinition() {
        super("file", "File utily command with multiple sub commands");
        ParameterSetDefinition set1 = new ParameterSetDefinition();
        set1.addParameterType(new ParameterType(ParameterType.Type.STRING));
        set1.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set1);
        
        ParameterSetDefinition set2 = new ParameterSetDefinition();
        set2.addParameterType(new ParameterType(ParameterType.Type.STRING));
        addParameterSetDefinition(set2);
    }
    
}
