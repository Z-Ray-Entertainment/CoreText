/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.command;

import de.zray.coretext.exceptions.InvalidParameterValueException;
import de.zray.coretext.exceptions.ParameterAmountException;
import de.zray.coretext.script.ScriptElement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public abstract class CommandDefinition {
    private String commandName, description;
    List<ParameterSetDefinition> sets;
    
    public CommandDefinition(String commandName, String description){
        this.commandName = commandName;
        this.description = description;
    }
    
    public final void addParameterSetDefinition(ParameterSetDefinition set){
        if(sets == null){
            sets = new LinkedList<>();
        }
        this.sets.add(set);
    }
    
    public final boolean matchParameters(List<ScriptElement> parameters){
        for(ParameterSetDefinition set : sets){
            if(set.match(parameters)){
                return true;
            }
        }
        return false;
    }
    
    public final boolean match(List<ScriptElement> elements){
        ScriptElement command = elements.get(0);
        
        if(command.getElementType() == ScriptElement.Type.COMMAND && command.getContent().equals(commandName)){
            for(ParameterSetDefinition set : sets){
                if(set.match(elements.subList(1, elements.size()))){
                    return true;
                }
            }
        }
        return false;
    }
    
    public final String getCMDName(){
        return commandName;
    }
    
    public final String getHelp(){
        String output = commandName+" ";
        for(ParameterSetDefinition set : sets){
            output += set.getHelp()+"\n";
        }
        return output;
    }
    
    public final boolean requireParameter(){
        if(sets == null){
            return false;
        }
        else{
            for(ParameterSetDefinition set : sets){
                if(!set.requireParameters()){
                    return false;
                }
            }
        }
        return true;
    }
    
    public final int getLowesAmount(){
        int lowes = sets.get(0).getAmount();
        for(ParameterSetDefinition set : sets){
            if(lowes > set.getAmount()){
                lowes = set.getAmount();
            }
        }
        return lowes;
    }
    
    public final boolean hasAmount(int amount){
        for(ParameterSetDefinition set : sets){
            if(set.getAmount() == amount){
                return true;
            }
        }
        return false;
    }
    
    public final List<Parameter> buildParameters(List<ScriptElement> elements) throws ParameterAmountException, InvalidParameterValueException{
        for(ParameterSetDefinition set : sets){
            if(set.match(elements)){
                System.out.println("[CMDDef]: found matching set has infinite: "+set.hasInfinite());
                return set.buildParametes(elements, set.hasInfinite());
            }
        }
        return null;
    }
}
