/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands;

import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.command.Parameter;
import de.zray.coretext.exceptions.AliasException;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Quit extends AbstractCommand{
    public Quit() throws AliasException {
        super(new QuitDefinition());
        addAlias("exit");
    }

    @Override
    public String action(List<Parameter> params) {
        System.exit(0);
        return "Quit";
    }
    
}
