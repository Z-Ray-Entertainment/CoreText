/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Parser {
    public List<ScriptElement> parseScript(String script){
        List<ScriptElement> elements = new LinkedList<>();
        int start = -1;
        boolean closeState = false, state = false, command = false;
        
        for(int i = 0; i < script.length(); i++){
            String curChar = script.substring(i, i+1);
            switch(curChar){
                case "<" :
                    if(start == -1){
                        state = true;
                        start = i;
                    }
                    break;
                case ">" :
                    if(start != -1){
                        String content = script.substring(start, i+1);
                        if(!closeState){
                            elements.add(new ScriptElement(ScriptElement.Type.OPEN_STATE, content));
                        }
                        else{
                            elements.add(new ScriptElement(ScriptElement.Type.CLOSE_STATE, content));
                            closeState = false;
                        }
                        start = -1;
                    }
                    break;
                case "/" :
                    if(state){
                        closeState = true;
                    }
                    break;
                case "(" :
                    elements.add(new ScriptElement(ScriptElement.Type.CLIP_OPEN, curChar));
                    break;
                case ")" :
                    elements.add(new ScriptElement(ScriptElement.Type.CLIP_CLOSE, curChar));
                    break;
                case "\"" :
                    elements.add(new ScriptElement(ScriptElement.Type.STRING_CHARACTER, curChar));
                    break;
                case "[" :
                    elements.add(new ScriptElement(ScriptElement.Type.CODE_START, curChar));
                    break;
                case "]" :
                    elements.add(new ScriptElement(ScriptElement.Type.CODE_END, curChar));
                    break;
                case ";" :
                    elements.add(new ScriptElement(ScriptElement.Type.COMMAD_END, curChar));
                    command = false;
                    break;
                case " " :
                    String content = script.substring(start, i);
                    if(!command){
                        elements.add(new ScriptElement(ScriptElement.Type.COMMAND, content));
                        command = true;
                    }
                    else{
                        elements.add(new ScriptElement(ScriptElement.Type.PARAMETER, content));
                    }
                    break;
                default : 
                    if(start != -1){
                        start = i;
                    }
                    break;
            }
        }
        return elements;
    }
}
