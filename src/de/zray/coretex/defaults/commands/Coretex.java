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
public class Coretex extends AbstractCommand{
    public Coretex() {
        super(new CoretexDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        switch(params.get(0).getValue()){
            case "version" :
                return "1.1";
        }
        return "";
    }
    
}
