/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.syntax;

import de.zray.coretex.exceptions.SyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vortex
 */
public class StateRule implements SyntaxRule{
    //<state>
    //</state>
    List<String> stateStack = new LinkedList<>();
    boolean newState = false, stateClosed = true;
    String currentState = "";
    
    @Override
    public void check(String currentCharacter) throws SyntaxException {
        switch(currentCharacter){
            case "<" :
                if(newState && stateClosed){
                    throw new SyntaxException("Error in StateSyntax: <state>...</state>");
                }
                else if (!stateClosed){
                    
                }
                newState = true;
                stateClosed = false;
                break;
            case ">" :
                if(newState && !stateClosed){
                    newState = false;
                    stateStack.add(currentState);
                }
                else{
                    throw new SyntaxException("Error in StateSyntax: <state>...</state>");
                }
                break;
            case "/" :
                break;
            default :
                if(newState){
                    currentState += currentCharacter;
                }
                break;
        }
    }

    @Override
    public void endOfScript() throws SyntaxException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
