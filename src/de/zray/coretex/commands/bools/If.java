/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.commands.bools;

import de.zray.coretex.AbstractCommand;
import de.zray.coretex.Parameter;
import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.InvalidTypeException;
import de.zray.coretex.exceptions.ParameterAmountException;
import de.zray.coretex.exceptions.SyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hester
 */
public class If extends AbstractCommand{

    public If() {
        super("if", null);
        Parameter param1 = new Parameter(Parameter.Type.BOOLEAN);
        Parameter param2 = new Parameter(Parameter.Type.CODEBLOCK);
        Parameter param3 = new Parameter(Parameter.Type.CODEBLOCK);
        List<Parameter> params = new LinkedList<>();
        params.add(param1);
        params.add(param2);
        params.add(param3);
        addParameters(params);
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
