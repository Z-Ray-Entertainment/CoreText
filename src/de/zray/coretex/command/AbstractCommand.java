/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.command;

import de.zray.coretex.Console;
import de.zray.coretex.exceptions.AliasException;
import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.ParameterAmountException;
import de.zray.coretex.script.ScriptElement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public abstract class AbstractCommand {
    private CommandDefinition definition;
    private List<String> aliases;
    private Console console;
    
    public AbstractCommand(CommandDefinition definition){
        this.definition = definition;
    }
    
    public String getCMDName(){
        return definition.getCMDName();
    }
    
    public void addAlias(String alias) throws AliasException {
        if(aliases == null){
            aliases = new LinkedList<>();
            aliases.add(alias);
        }
        else{
            if(!hasAlias(alias)){
                if(aliases == null){
                    aliases = new LinkedList<>();
                }
                aliases.add(alias);
            }
            else{
                throw new AliasException(alias);
            }
        }
    }
    
    public boolean matchesName(String name){
        if(definition.getCMDName().equals(name)){
            return true;
        }
        return hasAlias(name);
    }
    
    public boolean hasAlias(String alias){
        if(!definition.getCMDName().equals(alias)){
            if(aliases != null){
                return this.aliases.stream().anyMatch((tmp) -> (tmp.equals(alias)));
            }
        }
        return false;
    }

    public List<String> getAliases(){
        return aliases;
    }
    
    public String getHelp(){
        return definition.getHelp();
    }
    
    public boolean requireParameters(){
        return definition.requireParameter();
    }
    
    public List<Parameter> buildParameters(List<ScriptElement> elements) throws InvalidParameterValueException, ParameterAmountException{
        if(definition.matchParameters(elements)){
            return definition.buildParameters(elements);
        }
        return null;
    }
    
    public String execute(List<Parameter> params){
            return action(params);
    }
    
    public Console getConsole(){
        return console;
    }
    
    public void setConsole(Console console){
        this.console = console;
    }
    
    public abstract String action(List<Parameter> params) ;
}
