/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.defaults.commands;

import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.command.Parameter;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Help extends AbstractCommand{
    public Help() {
        super(new HelpDefinition());
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
                for(AbstractCommand cmd : getConsole().getCommandStorage().getCommands()){
                    if(cmd.getCMDName().equals(params.get(0).getValue())){
                        return output += cmd.getHelp()+"\n";
                    }
                }
                return params.get(0).getValue()+" not found, tipe 'help all' to see all commands.";
        }
    }
    
    private String getHelpOfAll(){
        String output = "";
        for(AbstractCommand cmd : getConsole().getCommandStorage().getCommands()){
            output += cmd.getHelp()+"\n";
        }
        if(output.isEmpty()){
            return "No Commands found.";
        }
        return output;
    }
}
