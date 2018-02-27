/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.InvalidTypeException;
import de.zray.coretex.exceptions.ParameterAmountException;
import de.zray.coretex.exceptions.SyntaxException;
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
        try {
            String thenBlock = params.get(1).getValue().substring(1, params.get(1).getValue().length()-1);
            System.out.println("THEN: "+thenBlock);
            String elseBlock = params.get(2).getValue().substring(1, params.get(2).getValue().length()-1);
            System.out.println("ELSE: "+elseBlock);
            switch(params.get(0).getValue()){
                case "true" :
                    return getConsole().executeScript(thenBlock+";");
                case "false" :
                    return getConsole().executeScript(elseBlock+";");
            }
        }
        catch (SyntaxException | ParameterAmountException | InvalidTypeException | InvalidParameterValueException ex) {
            return ex.getMessage();
        }
        return "";
    }
    
}
