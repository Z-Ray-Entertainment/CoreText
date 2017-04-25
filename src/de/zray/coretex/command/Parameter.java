/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.command;

import de.zray.coretex.exceptions.InvalidParameterValueException;

/**
 *
 * @author Vortex Acherontic
 */
public class Parameter {
    public enum Type{STRING, INTEGER, FLOAT, DOUBLE, UNDEFINED, EMPTY, INFINITE, BOOLEAN, CODEBLOCK}
    private Type type = Type.UNDEFINED;
    private String value;
    
    public Parameter(Type type){
        this.type = type;
    }
    
    public Type getType(){
        return type;
    }
    
    public void setValue(String universalInput) throws InvalidParameterValueException{
        switch(this.type){
            case DOUBLE :
                try{
                    Double.parseDouble(universalInput);
                }
                catch(NumberFormatException e){
                    throw new InvalidParameterValueException(universalInput+" is invalid DOUBLE expected.");
                }
                break;
            case FLOAT :
                try{
                   Float.parseFloat(universalInput);
                }
                catch(NumberFormatException e){
                    throw new InvalidParameterValueException(universalInput+" is invalid FLOAT expected.");
                }
                break;
            case INTEGER :
                try{
                    Integer.parseInt(universalInput);
                }
                catch(NumberFormatException e){
                    throw new InvalidParameterValueException(universalInput+" is invalid INTEGER expected.");
                }
                break;
            case EMPTY :
                throw new InvalidParameterValueException("Parameter of Type Empty can not have a value");
            case STRING :
                //Do nothing
                break;
            case UNDEFINED :
                throw new InvalidParameterValueException(Type.UNDEFINED, Type.UNDEFINED);
        }
        this.value = universalInput;
    }
    
    public void set(int value) throws InvalidParameterValueException{
        if(type != Type.INTEGER){
            throw new InvalidParameterValueException(Type.INTEGER, type);
        }
        this.value = String.valueOf(value);
    }
    
    public void set(float value) throws InvalidParameterValueException{
        if(type != Type.FLOAT){
            throw new InvalidParameterValueException(Type.FLOAT, type);
        }
        this.value = String.valueOf(value);
    }
    
    public void set(double value) throws InvalidParameterValueException{
        if(type != Type.DOUBLE){
            throw new InvalidParameterValueException(Type.DOUBLE, type);
        }
        this.value = String.valueOf(value);
    }
    
    public void set(String value) throws InvalidParameterValueException{
        if(type != Type.STRING){
            throw new InvalidParameterValueException(Type.STRING, type);
        }
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
