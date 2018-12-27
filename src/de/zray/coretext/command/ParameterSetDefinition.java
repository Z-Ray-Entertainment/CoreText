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
public class ParameterSetDefinition {
    private List<ParameterType> validParameters;
    
    public void addParameterType(ParameterType type){
        if(validParameters == null){
            validParameters = new LinkedList<>();
        }
        validParameters.add(type);
    }
    
    public boolean match(List<ScriptElement> elements){
        for(ScriptElement tmp : elements){
            for(ParameterType type : validParameters){
                if(!validateParameter(type, tmp)){
                    return false;
                }
            }
            if(hasInfinite()){
                return true;
            } else if(elements.size() == validParameters.size()){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    private boolean validateParameter(ParameterType type, ScriptElement element){
        if(hasInfinite()){
            return true;
        }
        switch(type.getType()){
            case BOOLEAN :
                try{
                    Boolean.parseBoolean(element.getContent());
                }
                catch(Exception e){
                    return false;
                }
                return true;
            case DOUBLE :
                try{
                    Double.parseDouble(element.getContent());
                }
                catch(NumberFormatException e){
                    return false;
                }
                return true;
            
            case FLOAT :
                try{
                    Float.parseFloat(element.getContent());
                }
                catch(NumberFormatException e){
                    return false;
                }
                return true;
            case INTEGER :
                try{
                    Integer.parseInt(element.getContent());
                }
                catch(NumberFormatException e){
                    return false;
                }
                return true;
            case STRING :
            case UNDEFINED :
            case EMPTY :
            case CODEBLOCK :
            case INFINITE :
                return true;
        }
        return false;
    }
    
    public String getHelp(){
        String output = "";
        for(ParameterType type : validParameters){
            output += type.toString()+" ";
        }
        return output.substring(0, output.length()-1);
    }
    
    public boolean requireParameters(){
        if(validParameters == null){
            return false;
        }
        else if(validParameters.get(0).getType() == ParameterType.Type.EMPTY){
            return false;
        }
        else{
            return true;
        }
    }
    
    public int getAmount(){
        return validParameters.size();
    }
    
    public List<Parameter> buildParametes(List<ScriptElement> elements, boolean infinite) throws ParameterAmountException, InvalidParameterValueException{
        boolean lastWasInfinite = false;
        if(!infinite && getAmount() != elements.size()){
            throw new ParameterAmountException(getAmount(), elements.size());
        }
        List<Parameter> params = new LinkedList<>();
        for(int i = 0; i < elements.size(); i++){
            String content = elements.get(i).getContent();
            ParameterType.Type paramType;
            if(!lastWasInfinite){
                paramType = validParameters.get(i).getType();
                if(paramType == ParameterType.Type.INFINITE){
                    lastWasInfinite = true;
                }
            }
            else {
                paramType = ParameterType.Type.STRING;
            }
            Parameter param = new Parameter(paramType);
            param.setValue(content);
            params.add(param);
        }
        return params;
    }
    
    public boolean hasInfinite(){
        return validParameters.stream().anyMatch((type) -> (type.getType() == ParameterType.Type.INFINITE));
    }
}
