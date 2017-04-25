/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands.algebra;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Add extends AbstractCommand{

    public Add() {
        super("add", null);
        Parameter var1 = new Parameter(Parameter.Type.DOUBLE);
        Parameter var2 = new Parameter(Parameter.Type.DOUBLE);
        List<Parameter> paramList = new LinkedList<>();
        paramList.add(var1);
        paramList.add(var2);
        
        Parameter var1I = new Parameter(Parameter.Type.INTEGER);
        Parameter var2I = new Parameter(Parameter.Type.INTEGER);
        List<Parameter> paramList2 = new LinkedList<>();
        paramList2.add(var1I);
        paramList2.add(var2I);
        
        Parameter var1F = new Parameter(Parameter.Type.FLOAT);
        Parameter var2F = new Parameter(Parameter.Type.FLOAT);
        List<Parameter> paramList3 = new LinkedList<>();
        paramList3.add(var1F);
        paramList3.add(var2F);
        
        addParameters(paramList);
        addParameters(paramList2);
        addParameters(paramList3);
    }

    @Override
    public String action(List<Parameter> params) {
        Parameter arg1 = params.get(0);
        Parameter arg2 = params.get(1);
        
        switch(arg1.getType()){
            case DOUBLE :
                double v1d = Double.parseDouble(arg1.getValue()), v2d = Double.parseDouble(arg2.getValue());
                return String.valueOf((v1d+v2d));
            case FLOAT :
                float v1f = Float.parseFloat(arg1.getValue()), v2f = Float.parseFloat(arg2.getValue());
                return String.valueOf((v1f+v2f));
            case INTEGER :
                float v1i = Float.parseFloat(arg1.getValue()), v2i = Float.parseFloat(arg2.getValue());
                return String.valueOf((v1i+v2i));
            case STRING :
                String v1s = arg1.getValue(), v2s = arg2.getValue();
                return v1s+v2s;
        }
        return "null";
    }
}
