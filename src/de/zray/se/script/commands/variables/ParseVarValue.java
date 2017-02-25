/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands.variables;

import de.zray.se.script.AbstractCommand;
import de.zray.se.script.Parameter;
import de.zray.se.script.exceptions.UnknownVariableException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class ParseVarValue extends AbstractCommand{

    public ParseVarValue() {
        super("$", null);
        Parameter var = new Parameter(Parameter.Type.STRING);
        List<Parameter> params = new LinkedList<>();
        params.add(var);
        addParameters(params);
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
