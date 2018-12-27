/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands.bool;

import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.command.Parameter;
import de.zray.coretext.exceptions.AliasException;
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
