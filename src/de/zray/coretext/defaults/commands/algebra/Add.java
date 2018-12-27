/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands.algebra;

import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.command.Parameter;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Add extends AbstractCommand{

    public Add() {
        super(new AddDefinition());
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
