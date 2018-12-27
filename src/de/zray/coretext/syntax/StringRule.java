/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.syntax;

import de.zray.coretext.exceptions.SyntaxException;

/**
 *
 * @author vortex
 */
public class StringRule implements SyntaxRule{
    boolean openString = false;

    @Override
    public void check(String currentCharacter) throws SyntaxException {
        switch(currentCharacter){
            case "\"" :
                openString = !openString;
                break;
        }
    }

    @Override
    public void endOfScript() throws SyntaxException {
        if(openString){
            throw new SyntaxException("There are open strings");
        }
    }

    @Override
    public void reset() {
        openString = false;
    }
}
