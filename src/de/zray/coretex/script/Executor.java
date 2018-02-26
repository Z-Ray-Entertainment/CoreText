/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script;

import de.zray.coretex.Console;
import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.InvalidTypeException;
import de.zray.coretex.exceptions.ParameterAmountException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Executor {
    private Console console;
    
    public Executor(Console console){
        this.console = console;
    }
    
    public String execute(List<ScriptElement> elememts) throws InvalidTypeException, InvalidParameterValueException, ParameterAmountException{
        List<ScriptElement> cmd = null;
        
        for(ScriptElement tmp : elememts){
            switch(tmp.getElementType()){
                case COMMAND :
                    if(cmd == null){
                        cmd = new LinkedList<>();
                        cmd.add(tmp);
                        System.out.println("[Executor]: Found command: "+tmp.getContent());
                    }
                    break;
                case PARAMETER :
                    if(cmd != null){
                        cmd.add(tmp);
                        System.out.println("[Executor]: Found parameter: "+tmp.getContent());
                    }
                    break;
                case COMMAD_END :
                    String cmdName;
                    if(cmd.get(0).getElementType() != ScriptElement.Type.COMMAND){
                        throw new InvalidTypeException("Failed to execute command, type of first ScriptElement is not COMMAND.");
                    }
                    cmdName = cmd.get(0).getContent();
                    AbstractCommand tmpCMD = console.getCommandStorage().findCommand(cmdName);
                    if(!(cmd.size() <= 1)){
                        List<ScriptElement> parameters = cmd.subList(1, cmd.size()-1);
                        tmpCMD.execute(tmpCMD.buildParameters(parameters));
                    }
                    else {
                        tmpCMD.execute(null);
                    }
                    break;
            }
        }
        return "Not implemented yet.";
    }
}
