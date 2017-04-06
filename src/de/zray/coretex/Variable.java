/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex;

import de.zray.coretex.exceptions.InvalidVarTypeException;

/**
 *
 * @author Vortex Acherontic
 */
public class Variable {
    public static enum Type {INTEGER, DOUBLE, FLOAT, STRING, BOOLEAN, COMMANDBLOCK};
    private String value = "", name = "";
    private Type type;
    
    public Variable(Type type, String name, String value) throws InvalidVarTypeException{
        this.type = type;
        this.name = name;
        setValue(value);
    }
    
    public void setValue(String value) throws InvalidVarTypeException{
        try{
            switch(type){
                case BOOLEAN :
                    if(!value.equals("true") && !value.equals("false")){
                        throw new InvalidVarTypeException(type);
                    }
                    break;
                case DOUBLE :
                    Double.parseDouble(value);
                    break;
                case FLOAT :
                    Float.parseFloat(value);
                    break;
                case INTEGER :
                    Integer.parseInt(value);
                    break;
                case COMMANDBLOCK :
                case STRING :
                    break;
            }
            this.value = value;
        }
        catch(NumberFormatException e){
            throw new InvalidVarTypeException(type);
        }
    }
    
    public boolean compare(String name){
        return this.name.equals(name);
    }
    
    public String getName(){
        return name;
    }
    
    public Type getType(){
        return type;
    }
    
    public String getValue(){
        return value;
    }
}
