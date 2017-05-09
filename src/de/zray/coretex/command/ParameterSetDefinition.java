/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.command;

import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.ParameterAmountException;
import de.zray.coretex.script.ScriptElement;
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
            switch(tmp.getElementType()){
                case CLIP_CLOSE :
                case CLIP_OPEN :
                case CLOSE_STATE :
                    return false;
                case CODE_END :
                    break;
                case CODE_START :
                    break;
                case COMMAD_END :
                    break;
                case COMMAND :
                    return false;
                case OPEN_STATE :
                    break;
                case PARAMETER :
                    break;
                case STRING_CHARACTER :
                    break;
                case UNDEFINED :
                    break;
            }
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
    
    public List<Parameter> buildParametes(List<ScriptElement> elements) throws ParameterAmountException, InvalidParameterValueException{
        if(getAmount() != elements.size()){
            throw new ParameterAmountException(getAmount(), elements.size());
        }
        List<Parameter> params = new LinkedList<>();
        for(int i = 0; i < elements.size(); i++){
            String content = elements.get(i).getContent();
            ParameterType.Type paramType = validParameters.get(i).getType();
            Parameter param = new Parameter(paramType);
            param.setValue(content);
            params.add(param);
        }
        return params;
    }
}
