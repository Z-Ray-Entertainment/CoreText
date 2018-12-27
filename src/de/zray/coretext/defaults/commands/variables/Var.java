/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands.variables;

import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.exceptions.InvalidVarTypeException;
import de.zray.coretext.command.Parameter;
import de.zray.coretext.Variable;
import de.zray.coretext.exceptions.DublicateVariableException;
import de.zray.coretext.exceptions.UnknownVariableException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        switch(params.size()){
            case 2 :
                String subCMD = params.get(0).getValue();
                String var = params.get(1).getValue();
                deleteVariable(subCMD, var);
                break;
            case 3 :
                String type, name, value;
                type = params.get(0).getValue();
                name = params.get(1).getValue();
                value = params.get(2).getValue();
                return createVariable(type, name, value);
        }
        return "";
    }
    
    private String deleteVariable(String subCMD, String variable){
        switch(subCMD){
            case "del" :
                getConsole().deleteVariable(variable);
                break;
        }
        return "[ERROR]: Subcommand "+subCMD+" not found.";
    }
    
    private String createVariable(String type, String name, String value){
        Variable var;
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
                    return "[ERROR]: Unknown variable type type "+type;
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
