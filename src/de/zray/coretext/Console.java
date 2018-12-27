/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext;

import de.zray.coretext.exceptions.AliasException;
import de.zray.coretext.exceptions.DublicateCommandException;
import de.zray.coretext.exceptions.DublicateSyntaxRuleException;
import de.zray.coretext.exceptions.DublicateVariableException;
import de.zray.coretext.exceptions.InvalidParameterValueException;
import de.zray.coretext.exceptions.InvalidTypeException;
import de.zray.coretext.exceptions.ParameterAmountException;
import de.zray.coretext.exceptions.SyntaxException;
import de.zray.coretext.exceptions.UnknownVariableException;
import de.zray.coretext.script.CommandStorage;
import de.zray.coretext.script.Executor;
import de.zray.coretext.script.Parser;
import de.zray.coretext.script.Validator;
import java.util.LinkedList;
import java.util.List;

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
    private List<String> history = new LinkedList<>();
    
    public Console() throws DublicateCommandException, DublicateSyntaxRuleException, AliasException{
        varStore = new VariableStore();
        parser = new Parser(this);
        validator = new Validator(true);
        executor = new Executor(this);
        cmdSotrage = new CommandStorage(this, true);
    }
        
    public String executeScript(String input) throws SyntaxException, ParameterAmountException, InvalidTypeException, InvalidParameterValueException, UnknownVariableException{
        validator.checkSyntax(input);
        history.add(input);
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
    
    public void deleteVariable(String name){
        varStore.deleteVariable(name);
    }
}
