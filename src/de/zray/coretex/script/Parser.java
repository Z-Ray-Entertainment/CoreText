/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script;

import de.zray.coretex.Console;
import de.zray.coretex.config.Indicators;
import de.zray.coretex.exceptions.UnknownVariableException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Parser {
    private Console console;
    
    public Parser(Console console){
        this.console = console;
    }
    
    private enum ParseState{
        READ_STATE, CLOSE_STATE, STRING, DEFAULT, CODE, PARAMETER, AFTER_CODE,
        AFTER_NESTED, PARSE_VAR
    }
    private ParseState state = ParseState.DEFAULT;

    public List<ScriptElement> parseScript(String script) throws UnknownVariableException{
        List<ScriptElement> elements = new LinkedList<>();
        int start = 0, openCodes = 0;
        
        for(int i = 0; i < script.length(); i++){
            String curChar = script.substring(i, i+1);
            switch(curChar){
                case Indicators.VARIABLE :
                    switch(state){
                        case CODE :
                            //Do nothing
                            break;
                        default:
                            start = i+1;
                            state = ParseState.PARSE_VAR;
                            break;
                    }
                    break;
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
                        case PARAMETER :
                            elements.add(new ScriptElement(ScriptElement.Type.CLIP_OPEN, curChar));
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.NESTED_COMMAND_END :
                    switch(state){
                        case DEFAULT :
                        case PARAMETER :
                            elements.add(new ScriptElement(ScriptElement.Type.CLIP_CLOSE, curChar));
                            start = i+1;
                            state = ParseState.AFTER_NESTED;
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
                        case PARAMETER :
                        case CODE :
                        case AFTER_CODE :
                            if(openCodes == 0){
                                start = i+1;
                                state = ParseState.CODE;
                            }
                            openCodes++;
                            break;
                    }
                    break;
                case Indicators.CODE_END :
                    switch(state){
                        case CODE :
                            openCodes--;
                            if(openCodes == 0){
                                elements.add(buildCodeBlock(script, start, i));
                                start = i+1;
                                state = ParseState.AFTER_CODE;
                            }                            
                            break;
                    }
                    break;
                case Indicators.COMMAND_END :
                    switch(state){
                        case DEFAULT :
                            if(start != i){
                                elements.add(buildCommand(script, start, i));
                                elements.add(new ScriptElement(ScriptElement.Type.COMMAD_END, curChar));
                            }
                            start = i+1;
                            break;
                        case PARAMETER :
                            state = ParseState.DEFAULT;
                            if(start != i){
                                elements.add(buildParameter(script, start, i));
                                elements.add(new ScriptElement(ScriptElement.Type.COMMAD_END, curChar));
                            }
                            start = i+1;
                            break;
                        case AFTER_CODE :
                        case AFTER_NESTED :
                            elements.add(new ScriptElement(ScriptElement.Type.COMMAD_END, curChar));
                            state = ParseState.DEFAULT;
                            start = i+1;
                            break;
                    }
                    break;
                case Indicators.SEPERATOR :
                    switch(state){
                        case DEFAULT :
                            if(start != i){
                                elements.add(buildCommand(script, start, i));
                                state = ParseState.PARAMETER;
                            }
                            start = i+1;
                            break;
                        case PARAMETER :
                            if(start != i){
                                elements.add(buildParameter(script, start, i));
                                state = ParseState.PARAMETER;
                            }
                            start = i+1;
                            break;
                        case AFTER_CODE :
                            state = ParseState.PARAMETER;
                            start = i+1;
                            break;
                        case AFTER_NESTED :
                            start = i+1;
                            state = ParseState.PARAMETER;
                            break;
                        case PARSE_VAR :
                            String var = script.substring(start, i);
                            String varValue = console.getVariable(var).getValue();
                            elements.add(new ScriptElement(ScriptElement.Type.PARAMETER, varValue));
                            state = ParseState.DEFAULT;
                            break;
                    }
                    break;
            }
        }
        System.out.println("[Parser]: Commands build: ");
        for(ScriptElement el : elements){
            System.out.println("-> "+el.getElementType()+" "+el.getContent());
        }
        state = ParseState.DEFAULT;
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
    
    private ScriptElement buildCodeBlock(String script, int start, int end){
        String content = script.substring(start, end);
        return new ScriptElement(ScriptElement.Type.CODEBLOCK, content);
    }
}
