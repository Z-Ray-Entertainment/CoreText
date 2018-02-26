/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands.algebra;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Substract extends AbstractCommand{

    public Substract() {
        super(new SubstractDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        double v1 = Double.parseDouble(params.get(0).getValue()), v2 = Double.parseDouble(params.get(1).getValue());
        return String.valueOf((v1-v2));
    }
    
}
