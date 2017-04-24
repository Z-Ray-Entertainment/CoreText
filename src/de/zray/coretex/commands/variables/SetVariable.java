/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.commands.variables;

import de.zray.coretex.AbstractCommand;
import de.zray.coretex.exceptions.InvalidVarTypeException;
import de.zray.coretex.Parameter;
import de.zray.coretex.exceptions.UnknownVariableException;
import java.util.LinkedList;
import java.util.List;

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
        return value;
    }
    
}