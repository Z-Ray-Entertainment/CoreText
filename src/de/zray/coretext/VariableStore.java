/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext;

import de.zray.coretext.exceptions.DublicateVariableException;
import de.zray.coretext.exceptions.UnknownVariableException;
import de.zray.coretext.exceptions.InvalidVarTypeException;
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
    
    public void deleteVariable(String name){
        for(Variable tmp : vars){
            if(tmp.compare(name)){
                vars.remove(tmp);
                System.out.println("[Var.Store.]: Deleted "+name);
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
    
    public void createVariable(Variable var) throws DublicateVariableException{
        for(Variable tmp : vars){
            if(tmp.getName().equals(var.getName())){
                throw new DublicateVariableException(var.getName());
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
