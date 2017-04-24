/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.syntax;

import de.zray.coretex.exceptions.SyntaxException;

/**
 *
 * @author vortex
 */
public class ClipRule implements SyntaxRule{
    int clips = 0;
    
    
    @Override
    public void check(String currentCharacter) throws SyntaxException {
        switch(currentCharacter){
            case "(" :
            case "[" :
            case "<" :
                clips++;
                break;
            case ")" :
            case "]" :
            case ">" :
                clips--;
                break;
        }
    }

    @Override
    public void endOfScript() throws SyntaxException {
        if(clips != 0){
            throw new SyntaxException("Open clips found");
        }
    }

    @Override
    public void reset() {
        clips = 0;
    }
    
}
