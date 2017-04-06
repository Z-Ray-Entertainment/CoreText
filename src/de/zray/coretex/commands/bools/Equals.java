/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.commands.bools;

import de.zray.coretex.AbstractCommand;
import de.zray.coretex.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Equals extends AbstractCommand{
    public Equals() {
        super("==", null);
        Parameter var1 = new Parameter(Parameter.Type.STRING);
        Parameter var2 = new Parameter(Parameter.Type.STRING);
        List<Parameter> paramList = new LinkedList<>();
        paramList.add(var1);
        paramList.add(var2);
        addParameters(paramList);
    }

    @Override
    public String action(List<Parameter> params) {
        if(params.get(0).getValue().equals(params.get(1).getValue())){
            return "true";
        }
        else{
            return "false";
        }
    }
    
}
