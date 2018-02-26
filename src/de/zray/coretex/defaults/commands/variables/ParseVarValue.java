/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands.variables;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import de.zray.coretex.exceptions.UnknownVariableException;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class ParseVarValue extends AbstractCommand{

    public ParseVarValue() {
        super(new ParseVarValueDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        String varName = params.get(0).getValue();
        try {
            return getConsole().getVariable(varName).getValue();
        }
        catch (UnknownVariableException ex) {
            return "Unknown variable "+varName;
        }
    }
    
}
