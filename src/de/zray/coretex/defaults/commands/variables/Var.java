/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands.variables;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.exceptions.InvalidVarTypeException;
import de.zray.coretex.command.Parameter;
import de.zray.coretex.Variable;
import de.zray.coretex.exceptions.DublicateVariableException;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Var extends AbstractCommand{

    public Var() {
        super(new VarDefinition());
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
            return "[ERROR]: "+value+" dose not matches type "+type+" for "+name;
        }
        catch(DublicateVariableException e){
            return "[ERROR]: "+name+" already exist";
        }
        return value;
    }
}
