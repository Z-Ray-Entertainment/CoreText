/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands.variables;

import de.zray.se.script.AbstractCommand;
import de.zray.se.script.exceptions.InvalidVarTypeException;
import de.zray.se.script.Parameter;
import de.zray.se.script.exceptions.UnknownVariableException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vortex Acherontic
 */
public class SetVariable extends AbstractCommand{

    public SetVariable() {
        super("=", null);
        Parameter var = new Parameter(Parameter.Type.STRING);
        Parameter value = new Parameter(Parameter.Type.STRING);
        List<Parameter> params = new LinkedList<>();
        params.add(var);
        params.add(value);
        addParameters(params);
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
        return "";
    }
    
}
