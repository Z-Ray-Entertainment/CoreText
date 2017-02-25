/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands.variables;

import de.zray.se.script.AbstractCommand;
import de.zray.se.script.exceptions.InvalidVarTypeException;
import de.zray.se.script.Parameter;
import de.zray.se.script.Variable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Var extends AbstractCommand{

    public Var() {
        super("var", null);
        Parameter type = new Parameter(Parameter.Type.STRING);
        Parameter name = new Parameter(Parameter.Type.STRING);
        Parameter value = new Parameter(Parameter.Type.STRING);
        List<Parameter> params = new LinkedList<>();
        params.add(type);
        params.add(name);
        params.add(value);
        addParameters(params);
    }

    @Override
    public String action(List<Parameter> params) {
        Variable var;
        String type, name, value;
        type = params.get(0).getValue();
        name = params.get(1).getValue();
        value = params.get(2).getValue();
        try{
            switch(type){
                case "int" :
                    var = new Variable(Variable.Type.INTEGER, name, value);
                    getConsole().createVar(var);
                    break;
                case "double" :
                    var = new Variable(Variable.Type.DOUBLE, name, value);
                    getConsole().createVar(var);
                    break;
                case "float" :
                    var = new Variable(Variable.Type.FLOAT, name, value);
                    getConsole().createVar(var);
                    break;
                case "string" :
                    var = new Variable(Variable.Type.STRING, name, value);
                    getConsole().createVar(var);
                    break;
                case "code" :
                    var = new Variable(Variable.Type.COMMANDBLOCK, name, value);
                    getConsole().createVar(var);
                    break;
                default:
                    return "Unknown type "+params.get(0).getValue();
            }
        }
        catch(InvalidVarTypeException e){
            return value+" dose not matches type "+type+" for "+name;
        }
        return "";
    }
    
}
