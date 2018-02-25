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
public interface SyntaxRule {

    /** Resieves one character from the Validator to be checked by this rule.
     * 
     * @param currentCharacter the current to be validated character
     * @throws de.zray.coretex.exceptions.SyntaxException
     */
    public void check(String currentCharacter) throws SyntaxException;

    /** Will be called at the end of the script file/string.
     * This is ment to be the last validation if any state is incorrect
     * 
     * @throws de.zray.coretex.exceptions.SyntaxException
    */
    public void endOfScript() throws SyntaxException;

    /**
     * This is called at the beginning of each script vaidation to rest this
     * rule to its default state
     */
    public void reset();
}
