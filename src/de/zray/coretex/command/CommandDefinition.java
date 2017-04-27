/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.command;

import de.zray.coretex.script.ScriptElement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class CommandDefinition {
    private String commandName, description;
    List<ParameterSetDefinition> sets;
    
    public CommandDefinition(String commandName, String description){
        this.commandName = commandName;
        this.description = description;
    }
    
    public void addParameterSetDefinition(ParameterSetDefinition set){
        if(sets == null){
            sets = new LinkedList<>();
        }
        this.sets.add(set);
    }
    
    public boolean match(List<ScriptElement> elements){
        ScriptElement command = elements.get(0);
        
        if(command.getElementType() == ScriptElement.Type.COMMAND && command.getContent().equals(commandName)){
            
        }
        return false;
    }
    
    public String getHelp(){
        String output = commandName+" ";
        for(ParameterSetDefinition set : sets){
            output += set.getHelp()+"\n";
        }
        return output;
    }
    
    public boolean requireParameter(){
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
}
