/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.script;

/**
 *
 * @author vortex
 */
public class ScriptElement {
    public static enum Type{
            OPEN_STATE, CLOSE_STATE, COMMAND, PARAMETER, CODEBLOCK, CLIP_OPEN, 
            CLIP_CLOSE, COMMAD_END, UNDEFINED
    };
    private String content;
    private Type type = Type.UNDEFINED;
    
    public ScriptElement(Type type, String content){
        this.content = content;
        this.type = type;
    }
    
    public Type getElementType(){
        return type;
    }
    
    public String getContent(){
        return content;
    }
}
