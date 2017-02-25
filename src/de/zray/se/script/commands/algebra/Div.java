/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands.algebra;

import de.zray.se.script.AbstractCommand;
import de.zray.se.script.Parameter;
import de.zray.se.script.ParameterSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Div extends AbstractCommand {

    public Div() {
        super("div", null);
        Parameter var1 = new Parameter(Parameter.Type.DOUBLE);
        Parameter var2 = new Parameter(Parameter.Type.DOUBLE);
        List<Parameter> paramList = new LinkedList<>();
        paramList.add(var1);
        paramList.add(var2);
        addParameters(paramList);
    }

    @Override
    public String action(List<Parameter> params) {
        double v1 = Double.parseDouble(params.get(0).getValue()), v2 = Double.parseDouble(params.get(1).getValue());
        return String.valueOf((v1/v2));
    }
    
}
