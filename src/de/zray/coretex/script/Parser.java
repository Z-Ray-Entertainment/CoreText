/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script;

import de.zray.coretex.config.Indicators;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Parser {
    private enum ParseState{READ_STATE, CLOSE_STATE, STRING, DEFAULT, CODE, PARAMETER}
    private ParseState state = ParseState.DEFAULT;
    
    public List<ScriptElement> parseScript(String script){
        List<ScriptElement> elements = new LinkedList<>();
        int start = 0;
        
        for(int i = 0; i < script.length(); i++){
            String curChar = script.substring(i, i+1);
            switch(curChar){
                case Indicators.STATE_OPEN :
                    switch(state){
                        case DEFAULT :
                            state = ParseState.READ_STATE;
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.STATE_CLOSE :
                    switch(state){
                        case READ_STATE :
                            String openStateName = script.substring(start, i);
                            elements.add(new ScriptElement(ScriptElement.Type.OPEN_STATE, openStateName));
                            state = ParseState.DEFAULT;
                            start = i+1;
                            break;
                        case CLOSE_STATE :
                            String closeStateName = script.substring(start, i);
                            elements.add(new ScriptElement(ScriptElement.Type.CLOSE_STATE, closeStateName));
                            state = ParseState.DEFAULT;
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.CLOSING_STATE :
                    switch(state){
                        case READ_STATE :
                            state = ParseState.CLOSE_STATE;
                            break;
                    }
                    break;
                case Indicators.NESTED_COMMAND_START :
                    switch(state){
                        case DEFAULT :
                            elements.add(new ScriptElement(ScriptElement.Type.CLIP_OPEN, curChar));
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.NESTED_COMMAND_END :
                    switch(state){
                        case DEFAULT :
                            elements.add(new ScriptElement(ScriptElement.Type.CLIP_CLOSE, curChar));
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.STRING :
                    switch(state){
                        case STRING :
                            state = ParseState.DEFAULT;
                            String string = script.substring(start, i);
                            elements.add(new ScriptElement(ScriptElement.Type.PARAMETER, string));
                            break;
                        case DEFAULT :
                        case PARAMETER :
                            state = ParseState.STRING;
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.CODE_START :
                    switch(state){
                        case DEFAULT :
                        case CODE :
                            state = ParseState.CODE;
                            elements.add(new ScriptElement(ScriptElement.Type.CODE_START, curChar));
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.CODE_END :
                    switch(state){
                        case CODE :
                            elements.add(new ScriptElement(ScriptElement.Type.CODE_END, curChar));
                            start = i+1;
                            state = ParseState.DEFAULT;
                            break;
                    }
                    break;
                case Indicators.COMMAND_END :
                    switch(state){
                        case DEFAULT :
                            elements.add(buildCommand(script, start, i));
                            elements.add(new ScriptElement(ScriptElement.Type.COMMAD_END, curChar));
                            start = i+1;
                            break;
                        case PARAMETER :
                            state = ParseState.DEFAULT;
                            elements.add(buildParameter(script, start, i));
                            elements.add(new ScriptElement(ScriptElement.Type.COMMAD_END, curChar));
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.SEPERATOR :
                    switch(state){
                        case DEFAULT :
                            elements.add(buildCommand(script, start, i));
                            state = ParseState.PARAMETER;
                            start = i+1;
                            break;
                        case PARAMETER :
                            elements.add(buildParameter(script, start, i));
                            state = ParseState.PARAMETER;
                            start = i+1;
                            break;
                    }
                    break;
            }
        }
        return elements;
    }
    
    private ScriptElement buildCommand(String script, int start, int end){
        String content = script.substring(start, end);
        return new ScriptElement(ScriptElement.Type.COMMAND, content);
    }
    
    private ScriptElement buildParameter(String script, int start, int end){
        String content = script.substring(start, end);
        return new ScriptElement(ScriptElement.Type.PARAMETER, content);
    }
}
