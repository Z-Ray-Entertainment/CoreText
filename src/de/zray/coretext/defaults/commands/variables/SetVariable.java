/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands.variables;

import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.exceptions.InvalidVarTypeException;
import de.zray.coretext.command.Parameter;
import de.zray.coretext.exceptions.UnknownVariableException;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class SetVariable extends AbstractCommand{

    public SetVariable() {
        super(new SetVariableDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        String name, value;
        name  = params.get(0).getValue();
        value = params.get(1).getValue();
        
        try {
            getConsole().getVariable(name).setValue(value);
        }
        catch (UnknownVariableException ex) {
            return "Variable "+name+" not found.";
        }
        catch (InvalidVarTypeException ex) {
           return value+" is no suitable type for "+name;
        }
        return value;
    }
    
}
