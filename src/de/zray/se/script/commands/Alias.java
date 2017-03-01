/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands;

import de.zray.se.script.AbstractCommand;
import de.zray.se.script.Parameter;
import de.zray.se.script.exceptions.AliasException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vortex Acherontic
 */
public class Alias extends AbstractCommand{
    
    public Alias() {
        super("alias", null);
        Parameter param1 = new Parameter(Parameter.Type.STRING);
        Parameter param2 = new Parameter(Parameter.Type.STRING);
        List<Parameter> params = new LinkedList<>();
        params.add(param1);
        params.add(param2);
        addParameters(params);
    }

    @Override
    public String action(List<Parameter> params) {
        String cmd = params.get(0).getValue();
        String alias = params.get(1).getValue();
        if(!aliasExsit(alias)){
            AbstractCommand tmp = getMatchingCommand(cmd);
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
    
    private AbstractCommand getMatchingCommand(String cmd){
        for(AbstractCommand tmp : getConsole().getCommands()){
            if(tmp.getRootCMD().equals(cmd)){
                return tmp;
            }
        }
        return null;
    }
    
    private boolean aliasExsit(String alias){
        return getConsole().getCommands().stream().anyMatch((tmp) -> (tmp.hasAlias(alias) || tmp.getRootCMD().equals(alias)));
    }
}
