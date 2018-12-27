/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands.bool;

import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.command.Parameter;
import de.zray.coretext.exceptions.InvalidParameterValueException;
import de.zray.coretext.exceptions.InvalidTypeException;
import de.zray.coretext.exceptions.ParameterAmountException;
import de.zray.coretext.exceptions.SyntaxException;
import de.zray.coretext.exceptions.UnknownVariableException;
import java.util.List;

/**
 *
 * @author hester
 */
public class If extends AbstractCommand{

    public If() {
        super(new IfDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        boolean condition = Boolean.parseBoolean(params.get(0).getValue());
        try {
            String thenBlock = params.get(1).getValue();
            System.out.println("THEN: "+thenBlock);
            String elseBlock = params.get(2).getValue();
            System.out.println("ELSE: "+elseBlock);
            if(condition){
                return getConsole().executeScript(thenBlock+";");
            } else {
                return getConsole().executeScript(elseBlock+";");
            }
        }
        catch (SyntaxException | ParameterAmountException | InvalidTypeException | InvalidParameterValueException | UnknownVariableException ex) {
            return ex.getMessage();
        }
    }
    
}
