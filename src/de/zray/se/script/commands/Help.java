/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script.commands;

import de.zray.se.script.AbstractCommand;
import de.zray.se.script.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Help extends AbstractCommand{
    public Help() {
        super("help", null);
        List<Parameter> params = new LinkedList<>();
        Parameter command = new Parameter(Parameter.Type.STRING);
        params.add(command);
        addParameters(params);
        List<Parameter> params2 = new LinkedList<>();
        Parameter empty = new Parameter(Parameter.Type.EMPTY);
        params2.add(empty);
        addParameters(params2);
    }

    @Override
    public String action(List<Parameter> params) {
        String output;
        output = "All available commands:\n";
        if(params == null){
            return getHelpOfAll();
        }
        switch(params.get(0).getValue()){
            case "commands" :
            case "all" :
                return getHelpOfAll();
            default:
                System.out.println(getConsole());
                for(AbstractCommand cmd : getConsole().getCommands()){
                    if(cmd.getRootCMD().equals(params.get(0).getValue())){
                        return output += cmd.getHelp()+"\n";
                    }
                }
                return params.get(0).getValue()+" not found, tipe 'help all' to see all commands.";
        }
    }
    
    private String getHelpOfAll(){
        String output = "";
        for(AbstractCommand cmd : getConsole().getCommands()){
            output += cmd.getHelp()+"\n";
        }
        if(output.isEmpty()){
            return "No Commands found.";
        }
        return output;
    }
}
