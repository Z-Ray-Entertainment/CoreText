/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script;

import de.zray.se.script.exceptions.UnknownVariableException;
import de.zray.se.script.exceptions.InvalidVarTypeException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class VariableStore {
    private List<Variable> vars = new LinkedList<>();
    
    public void setVariable(String name, String value) throws InvalidVarTypeException{
        for(Variable tmp : vars){
            if(tmp.compare(name)){
                tmp.setValue(value);
                return;
            }
        }
    }
    
    public void addVariable(Variable var){
        for(Variable tmp : vars){
            if(tmp.compare(var.getName())){
                tmp = var;
                return;
            }
        }
        vars.add(var);
    }
    
    public Variable getVariable(String name) throws UnknownVariableException{
        for(Variable tmp : vars){
            if(tmp.compare(name)){
                return tmp;
            }
        }
        throw new UnknownVariableException(name);
    }
}
