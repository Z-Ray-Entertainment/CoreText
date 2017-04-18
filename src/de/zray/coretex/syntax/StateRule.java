/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.syntax;

import de.zray.coretex.exceptions.SyntaxException;
import java.util.Stack;

/**
 * Copyright by Imo "Vortex Acherontic" Hester and/or Z-Ray Entertainment
 * @company Z-Ray Entertainment
 * @author vortex
 */
public class StateRule implements SyntaxRule{
    private static enum Mode{WAIT_FOR_OPENING_STATE, READ_OPENING_STATE, 
    WAIT_FOR_CLOSEING_STATE, READING_CLOSING_STATE, SUB_STATE, DONE};
    //<state>
    //</state>
    String currentState = "", lastState = "";
    Mode mode = Mode.WAIT_FOR_OPENING_STATE;
    StateRule subState;
    boolean initedByParent = false;
    
    public StateRule(){}
    public StateRule(String state){
        initedByParent = true;
        lastState = state;
    }
    
    public Mode getCurrentMode(){
        return mode;
    }
    
    @Override
    public void check(String currentCharacter) throws SyntaxException {
        if(mode == Mode.SUB_STATE){
            if(subState != null){
                subState = new StateRule();
            }
            subState.check(currentCharacter);
            switch(subState.getCurrentMode()){
                case DONE :
                    mode = Mode.WAIT_FOR_CLOSEING_STATE;
                    break;
            }
        }
        else{
            switch(currentCharacter){
                case "<" :
                    switch(mode){
                        case WAIT_FOR_OPENING_STATE :
                            mode = Mode.READ_OPENING_STATE;
                            break;
                        case READ_OPENING_STATE :
                            throw new SyntaxException("< is not allowd within a state name.");
                        case WAIT_FOR_CLOSEING_STATE :
                            mode = Mode.READING_CLOSING_STATE;
                            break;
                        case READING_CLOSING_STATE :
                            throw new SyntaxException("< is not allowd within a state name.");
                    }
                    break;
                case ">" :
                    switch(mode){
                        case WAIT_FOR_OPENING_STATE :
                            throw new SyntaxException("> is not a valid character outside a state.");
                        case READ_OPENING_STATE :
                            mode = Mode.WAIT_FOR_CLOSEING_STATE;
                            lastState = currentState;
                            break;
                        case WAIT_FOR_CLOSEING_STATE :
                            throw new SyntaxException("> is not a valid Character outside a state");
                        case READING_CLOSING_STATE :
                            if(!lastState.equals(currentState)){
                                mode = Mode.SUB_STATE;
                                subState = new StateRule(currentState);
                                currentState = "";
                            }
                            else{
                                if(initedByParent){
                                    mode = Mode.DONE;
                                }
                                else{
                                    reset();
                                }
                            }
                            break;
                    } 
                    break;
                case "/" :
                    switch(mode){
                        case WAIT_FOR_OPENING_STATE :
                            throw new SyntaxException("/ is not a valid character outside a state");
                        case READ_OPENING_STATE :
                            throw new SyntaxException("/ is not a valid character in an opening state");
                        case WAIT_FOR_CLOSEING_STATE :
                            throw new SyntaxException("/ is not a valid character outside a state");
                        case READING_CLOSING_STATE :
                            break;
                    }
                default :
                    switch(mode){
                        case WAIT_FOR_CLOSEING_STATE :
                        case WAIT_FOR_OPENING_STATE :
                            break;
                        case READING_CLOSING_STATE :
                        case READ_OPENING_STATE :
                            currentState += currentCharacter;
                            break;
                    }
                    break;
            }
        }
        
    }

    @Override
    public void endOfScript() throws SyntaxException {
        if(mode != Mode.WAIT_FOR_OPENING_STATE || !currentState.isEmpty() || !lastState.isEmpty()){
            throw new SyntaxException("States are not valid.");
        }
    }

    @Override
    public void reset() {
        mode = Mode.WAIT_FOR_OPENING_STATE;
        currentState = "";
        lastState = "";
    }
    
}
