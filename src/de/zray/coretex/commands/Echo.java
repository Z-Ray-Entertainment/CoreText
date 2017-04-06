/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.commands;

import de.zray.coretex.AbstractCommand;
import de.zray.coretex.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Echo extends AbstractCommand{
    public Echo() {
        super("echo", null);
        List<Parameter> params = new LinkedList<>();
        Parameter val1 = new Parameter(Parameter.Type.STRING);
        params.add(val1);
        this.addParameters(params);
    }

    @Override
    public String action(List<Parameter> params) {
        return "echo: "+params.get(0).getValue();
    }
    
}
