/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script;

import de.zray.se.script.exceptions.InvalidParameterValueException;
import de.zray.se.script.exceptions.ParameterAmountException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public abstract class AbstractCommand {
    private String cmd = "DEFAULT", customHelp, description = "No description.";
    private List<ParameterSet> parameterSets;
    private Console console;
    
    public AbstractCommand(String cmd, List<Parameter> params){
        this.cmd = cmd;
        if(params != null){
            this.parameterSets = new LinkedList<>();
            ParameterSet parameterSet = new ParameterSet();
            parameterSet.setParameters(params);
            this.parameterSets.add(parameterSet);
        }
    }
    
    public void setConsole(Console console){
        System.out.println(getRootCMD()+" Console set "+console);
        this.console = console;
    }
    
    public Console getConsole(){
        return this.console;
    }
    
    public void addParameterSet(ParameterSet set){
        if(parameterSets == null){
            parameterSets = new LinkedList<>();
        }
        this.parameterSets.add(set);
    }
    
    public void addParameters(List<Parameter> params){
        ParameterSet set = new ParameterSet();
        set.setParameters(params);
        if(this.parameterSets == null){
            this.parameterSets = new LinkedList<>();
        }
        this.parameterSets.add(set);
    }
    
    public String getRootCMD(){
        return cmd;
    }

    public String getHelp(){
        if(customHelp == null){
            String output = getRootCMD();
            if(parameterSets != null){
                for(ParameterSet tmp : parameterSets){
                    output += tmp.getParameterTypes()+"\n... ";
                    return output.substring(0, output.length()-5);
                }
            }
            return output;
        }
        else{
            return customHelp;
        }
    }
    
    public boolean requireParameters(){
        if(parameterSets == null){
            return false;
        }
        else{
            for(ParameterSet set : parameterSets){
               for(Parameter tmp : set.getEmptyParameters()){
                    if(tmp.getType() == Parameter.Type.EMPTY){
                        return false;
                    }
                } 
            }
        }
        return true;
    }
    
    public List<Parameter> buildParameters(List<String> input) throws InvalidParameterValueException, ParameterAmountException{
        for(ParameterSet tmp : parameterSets){
            try{
                List<Parameter> buildedParams = tmp.buildParameters(input);
                System.out.println("BuildedParams: "+buildedParams.toString());
                return buildedParams;
            }
            catch(InvalidParameterValueException e){
                //Try next Set
            }
        }
        throw new InvalidParameterValueException("The given Input dose not match any of the given ParameterSets.");
    }
    
    public String execute(List<Parameter> params){
            return action(params);
    }
    
    public abstract String action(List<Parameter> params);
}
