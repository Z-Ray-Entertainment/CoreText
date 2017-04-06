/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands;

import de.zray.coretex.AbstractCommand;
import de.zray.coretex.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Coretex extends AbstractCommand{

    public Coretex() {
        super("coretex", null);
        Parameter param1 = new Parameter(Parameter.Type.STRING);
        List<Parameter> params = new LinkedList<>();
        params.add(param1);
        addParameters(params);
    }

    @Override
    public String action(List<Parameter> params) {
        switch(params.get(0).getValue()){
            case "version" :
                return "1.0";
        }
        return "";
    }
    
}
