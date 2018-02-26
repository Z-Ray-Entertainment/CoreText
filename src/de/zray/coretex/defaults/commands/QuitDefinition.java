/*
 * Copyright by Z-Ray Entertainment 2018
 * Unauthorized copying or distributing of this source is not allowed
 * for further informations contact: support@z-ray.de
 */
package de.zray.coretex.defaults.commands;

import de.zray.coretex.command.CommandDefinition;

/**
 *
 * @author vortex
 */
public class QuitDefinition extends CommandDefinition{
    
    public QuitDefinition() {
        super("quit", "This command will exit cortext and any running application which implements this library");
    }
    
}
