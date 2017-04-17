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
    private static final int IDLE = 0, READING_STATE = 1, WAIT_FOR_STATE_TO_CLOSE = 2, READING_CLOSE_STATE = 4;
    //<state>
    //</state>
    Stack<String> stateStack = new Stack<>();
    String currentState = "";
    int mode = IDLE;
    boolean increase = true;
    
    @Override
    public void check(String currentCharacter) throws SyntaxException {
        switch(currentCharacter){
            case "<" :
                
                break;
            case ">" :
                
                break;
            case "/" :
               
                break;
            default :
                
                break;
        }
    }

    @Override
    public void endOfScript() throws SyntaxException {
       
    }

    @Override
    public void reset() {
    }
    
}
