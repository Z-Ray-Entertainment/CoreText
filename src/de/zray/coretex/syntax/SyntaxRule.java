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
 public void check(String currentCharacter) throws SyntaxException;
 public void endOfScript() throws SyntaxException;
 public void reset();
}
