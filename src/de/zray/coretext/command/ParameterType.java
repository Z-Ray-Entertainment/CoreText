/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.command;

/**
 *
 * @author Vortex Acherontic
 */
public class ParameterType {
    public static enum Type{STRING, FLOAT, DOUBLE, INTEGER, BOOLEAN, 
        CODEBLOCK, INFINITE, EMPTY, UNDEFINED};
    private Type type = Type.UNDEFINED;
    
    public ParameterType(Type type){
        this.type = type;
    }
    
    public boolean suitsType(String parameterValue){
        try{
            switch(type){
                case BOOLEAN :
                    return Boolean.parseBoolean(parameterValue);
                case DOUBLE :
                    Double.parseDouble(parameterValue);
                    return true;
                case FLOAT :
                    Float.parseFloat(parameterValue);
                    return true;
                case INTEGER :
                    Integer.parseInt(parameterValue);
                    return true;
                case STRING :
                case CODEBLOCK :
                case EMPTY :
                case INFINITE :
                case UNDEFINED :
                    return true;

            }
        }
        catch(NumberFormatException e){
            return false;
        }
        return false;
    }
    
    public Type getType(){
        return type;
    }
}
