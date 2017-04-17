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
    //<state>
    //</state>
    Stack<String> stateStack = new Stack<>();
    String currentState = "";
    int mode = 0;
    boolean increase = true;
    
    @Override
    public void check(String currentCharacter) throws SyntaxException {
        switch(currentCharacter){
            case "<" :
                if(newState && stateClosed){
                    throw new SyntaxException("Error in StateSyntax expected something like: <state>...</state>");
                }
                else if (!stateClosed && !newState){
                    mayClosed = true;
                }
                newState = true;
                stateClosed = false;
                break;
            case ">" :
                if(newState && !stateClosed){
                    newState = false;
                    stateStack.add(currentState);
                    currentState = "";
                }
                else if(mayClosed && willClose){
                    String lastState = stateStack.pop();
                    if(!lastState.equals(currentState)){
                        throw new SyntaxException("The wrong state was closed, expected: "+lastState+" found: "+currentState);
                    }
                }
                else if(newState){
                    
                }
                else{
                    throw new SyntaxException("Error in StateSyntax: <state>...</state>");
                }
                break;
            case "/" :
                if(!newState && !stateClosed && mayClosed){
                    willClose = true;
                }
                break;
            default :
                if((newState && !stateClosed) || (mayClosed || willClose)){
                    currentState += currentCharacter;
                }
                break;
        }
    }

    @Override
    public void endOfScript() throws SyntaxException {
        if(newState || !stateClosed || mayClosed || willClose || !currentState.isEmpty()){
            throw new SyntaxException("There are open states.");
        }
    }

    @Override
    public void reset() {
        newState = false;
        stateClosed = true;
        mayClosed = false;
        willClose = false;
        currentState = "";
    }
    
}
