/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import de.zray.coretex.exceptions.AliasException;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Alias extends AbstractCommand{
    
    public Alias() {
        super(new AliasDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        String cmd = params.get(0).getValue();
        String alias = params.get(1).getValue();
        if(!aliasExsit(alias)){
            AbstractCommand tmp = getConsole().getCommandStorage().findCommand(cmd);
            if(tmp != null){
                try {
                    tmp.addAlias(alias);
                    return "Alias: "+alias+" set for command: "+cmd;
                }
                catch (AliasException ex) {
                    return ex.getMessage();
                }
            }
        }
        return "Alias already exist";
    }
    
    private boolean aliasExsit(String alias){
        for(AbstractCommand cmd : getConsole().getCommandStorage().getCommands()){
            if(cmd.matchesName(alias)){
                return true;
            }
        }
        return false;
    }
}
