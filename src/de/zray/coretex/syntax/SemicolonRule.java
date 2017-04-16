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
public class SemicolonRule implements SyntaxRule{
    boolean semicolon = false;

    @Override
    public void check(String currentCharacter) throws SyntaxException {
        switch(currentCharacter){
            case ";" :
                semicolon = true;
                System.out.println("Found Semicolon");
                break;
        }
    }

    @Override
    public void endOfScript() throws SyntaxException {
        if(!semicolon){
            throw new SyntaxException("No semicolon in script");
        }
    }

    @Override
    public void reset() {
        semicolon = false;
    }
}
