/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.syntax;

import de.zray.coretex.exceptions.SyntaxException;

/**
 * Copyright by Imo "Vortex Acherontic" Hester and/or Z-Ray Entertainment
 * @company Z-Ray Entertainment
 * @author vortex
 */
public class StateRule implements SyntaxRule{
    private static enum Mode{WAIT_FOR_OPENING_STATE, READ_OPENING_STATE, 
    WAIT_FOR_CLOSEING_STATE, READING_CLOSING_STATE, DONE, STATE_MAY_CLOSE, SUB_STATE};
    //<state>
    //</state>
    String currentState = "", lastState = "";
    Mode mode = Mode.WAIT_FOR_OPENING_STATE;
    StateRule subState;
    boolean string = false;
    
    public StateRule(){}
    public StateRule(Mode stateMode){
        this.mode = stateMode;
    }
    
    public Mode getCurrentMode(){
        return mode;
    }
    
    @Override
    public void check(String currentCharacter) throws SyntaxException {
        if(subState != null){
            subState.check(currentCharacter);
            if(subState.getCurrentMode() == Mode.DONE){
                subState = null;
                check(currentCharacter);
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
                        case READING_CLOSING_STATE :
                            throw new SyntaxException(currentCharacter+" is not a allowed character with in a state name.");
                        case WAIT_FOR_CLOSEING_STATE :
                        case SUB_STATE :
                            mode = Mode.STATE_MAY_CLOSE;
                            break;
                        case STATE_MAY_CLOSE :
                            throw new SyntaxException(currentCharacter+" is not allowed here, expected / for closing the current state.");
                    }
                    break;
                case ">" :
                    switch(mode){
                        case WAIT_FOR_CLOSEING_STATE :
                        case WAIT_FOR_OPENING_STATE :
                            if(!string){
                                throw new SyntaxException(currentCharacter+" is not allowed outside a string or for indicating the next state.");
                            }
                        case READ_OPENING_STATE :
                            lastState = currentState;
                            currentState = "";
                            mode = Mode.WAIT_FOR_CLOSEING_STATE;
                            break;
                        case READING_CLOSING_STATE :
                            if(currentState.equals(lastState)){
                                mode = Mode.DONE;
                            }
                            else{
                                throw new SyntaxException(lastState+" was expected but "+currentState+" found.");
                            }
                            break;
                    }
                    break;
                case "/" :
                    switch(mode){
                        case STATE_MAY_CLOSE :
                            mode = Mode.READING_CLOSING_STATE;
                            break;
                        case READING_CLOSING_STATE :
                        case READ_OPENING_STATE :
                        case WAIT_FOR_CLOSEING_STATE :
                        case WAIT_FOR_OPENING_STATE :
                            if(!string){
                                throw new SyntaxException(currentCharacter+" is not allowed outside a string, only to indicate a state to close.");
                            }
                            break;
                    }
                    break;
                case "\"" :
                    string = !string;
                    break;
                default:
                    switch(mode){
                        case READING_CLOSING_STATE :
                        case READ_OPENING_STATE :
                            currentState += currentCharacter;
                            break;
                        case STATE_MAY_CLOSE :
                            subState = new StateRule(Mode.READ_OPENING_STATE);
                            subState.check(currentCharacter);
                            mode = Mode.SUB_STATE;
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void endOfScript() throws SyntaxException {
        if(mode != Mode.DONE){
            if(mode != Mode.WAIT_FOR_OPENING_STATE){
                throw new SyntaxException(lastState+" was not closed.");
            }
        }
    }

    @Override
    public void reset() {
        mode = Mode.WAIT_FOR_OPENING_STATE;
        currentState = "";
        lastState = "";
        string = false;
    }
    
}
