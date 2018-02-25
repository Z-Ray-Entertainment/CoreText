/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands.bool;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import de.zray.coretex.exceptions.AliasException;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class NotEqual extends AbstractCommand{
    public NotEqual() throws AliasException {
        super(new NotEqualsDefinition());
        addAlias("not");
    }

    @Override
    public String action(List<Parameter> params) {
        if(params.get(0).getValue().equals(params.get(1).getValue())){
            return "false";
        }
        else{
            return "true";
        }
    }
    
}
