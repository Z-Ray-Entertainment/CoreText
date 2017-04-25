/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.command;

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
        switch(type){
            case BOOLEAN :
                break;
        }
        return false;
    }
}
