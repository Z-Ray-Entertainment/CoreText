/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex;

import de.zray.coretex.exceptions.AliasException;
import de.zray.coretex.exceptions.DublicateCommandException;
import de.zray.coretex.exceptions.DublicateSyntaxRuleException;
import de.zray.coretex.exceptions.DublicateVariableException;
import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.InvalidTypeException;
import de.zray.coretex.exceptions.ParameterAmountException;
import de.zray.coretex.exceptions.SyntaxException;
import de.zray.coretex.exceptions.UnknownVariableException;
import de.zray.coretex.script.CommandStorage;
import de.zray.coretex.script.Executor;
import de.zray.coretex.script.Parser;
import de.zray.coretex.script.Validator;

/**
 * Copyright by Imo "Vortex Acherontic" Hester and/or Z-Ray Entertainment
 * @company Z-Ray Entertainment
 * @author Vortex Acherontic
 */
public class Console {
    private final VariableStore varStore;
    private final Parser parser;
    private final Validator validator;
    private final Executor executor;
    private final CommandStorage cmdSotrage;
    
    public Console() throws DublicateCommandException, DublicateSyntaxRuleException, AliasException{
        varStore = new VariableStore();
        parser = new Parser();
        validator = new Validator(true);
        executor = new Executor(this);
        cmdSotrage = new CommandStorage(this, true);
    }
        
    public String executeScript(String input) throws SyntaxException, ParameterAmountException, InvalidTypeException, InvalidParameterValueException{
        validator.checkSyntax(input);
        return executor.execute(parser.parseScript(input));
    }
    
    public CommandStorage getCommandStorage(){
        return cmdSotrage;
    }
    
    public Variable getVariable(String varName) throws UnknownVariableException {
        return varStore.getVariable(varName);
    }
    
    public void createVar(Variable var) throws DublicateVariableException {
        varStore.createVariable(var);
    }
}
