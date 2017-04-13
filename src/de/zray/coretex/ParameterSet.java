/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex;

import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.ParameterAmountException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class ParameterSet {
    private List<Parameter> parameters;
    
    public void setParameters(List<Parameter> parameters){
        this.parameters = new LinkedList<>();
        this.parameters.addAll(parameters);
    }
    
    public boolean matches(List<Parameter> parameters){
        if(parameters.size() != this.parameters.size()){
            return false;
        }
        else{
            for(int i = 0; i < this.parameters.size(); i++){
                if(this.parameters.get(i).getType() != parameters.get(i).getType()){
                    return false;
                }
            }
        }
        return true;
    }
    
    public List<Parameter> buildParameters(List<String> parameters) throws InvalidParameterValueException, ParameterAmountException{
        if(parameters.size() != this.parameters.size()){
            throw new ParameterAmountException(this.parameters.size(), parameters.size(), parameters.toString());
        }
        List<Parameter> buildedParams = new LinkedList<>();
        for(int i = 0; i < this.parameters.size(); i++){
            Parameter tmpParam = new Parameter(this.parameters.get(i).getType());
            tmpParam.setValue(parameters.get(i));
            buildedParams.add(tmpParam);
            System.out.println("Builded Param: "+tmpParam.getValue());
        }
        return buildedParams;
    }
    
    public List<Parameter> getEmptyParameters(){
        return parameters;
    }
    
    public String getParameterTypes(){
        String output = "";
        for(Parameter tmp : parameters){
            output += " "+tmp.getType().name();
        }
        return output;
    }
}
