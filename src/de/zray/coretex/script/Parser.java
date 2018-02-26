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
        int start = 0;
        boolean closeState = false, state = false, command = true;
        
        for(int i = 0; i < script.length(); i++){
            String curChar = script.substring(i, i+1);
            switch(curChar){
                case "<" :
                    if(start == -1){
                        state = true;
                        command = false;
                        start = i;
                    }
                    break;
                case ">" :
                    if(start != -1){
                        String content = script.substring(start, i+1);
                        if(!closeState){
                            elements.add(new ScriptElement(ScriptElement.Type.OPEN_STATE, content));
                            System.out.println("[Parser]: Found opening state: "+content);
                            command = true;
                        }
                        else{
                            elements.add(new ScriptElement(ScriptElement.Type.CLOSE_STATE, content));
                            closeState = false;
                            command = true;
                            System.out.println("[Parser]: Found closing state: "+content);
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
                    start = i+1;
                    break;
                case ")" :
                    elements.add(new ScriptElement(ScriptElement.Type.CLIP_CLOSE, curChar));
                    start = i+1;
                    break;
                case "\"" :
                    elements.add(new ScriptElement(ScriptElement.Type.STRING_CHARACTER, curChar));
                    start = i+1;
                    break;
                case "[" :
                    elements.add(new ScriptElement(ScriptElement.Type.CODE_START, curChar));
                    start = i+1;
                    break;
                case "]" :
                    elements.add(new ScriptElement(ScriptElement.Type.CODE_END, curChar));
                    start = i+1;
                    break;
                case ";" :
                    if(!command && !closeState && !state){
                        elements.add(buildParameter(script, start, i));
                    } else if(command && !closeState && !state){
                        elements.add(buildCommand(script, start, i));
                    }
                    elements.add(new ScriptElement(ScriptElement.Type.COMMAD_END, curChar));
                    start = i+1;
                    break;
                case " " :
                    if(command){
                        elements.add(buildCommand(script, start, i));
                        command = false;
                        start = i+1;
                    }
                    else{
                        elements.add(buildParameter(script, start, i));
                        start = i+1;
                    }
                    break;
            }
        }
        return elements;
    }
    
    private ScriptElement buildCommand(String script, int start, int end){
        String content = script.substring(start, end);
        System.out.println("[Parser]: Found command: "+content);
        return new ScriptElement(ScriptElement.Type.COMMAND, content);
    }
    
    private ScriptElement buildParameter(String script, int start, int end){
        String content = script.substring(start, end);
        System.out.println("[Parser]: Found parameter: "+content);
        return new ScriptElement(ScriptElement.Type.PARAMETER, content);
    }
}
