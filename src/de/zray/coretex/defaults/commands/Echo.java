/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Echo extends AbstractCommand{
    public Echo() {
        super(new EchoDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((par) -> {
            sb.append(par.getValue());
        });
        return "echo: "+sb.toString();
    }
    
}
