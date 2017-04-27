/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script;

import de.zray.coretex.Console;
import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.exceptions.InvalidTypeException;
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
    
    public String execute(List<ScriptElement> elememts) throws InvalidTypeException{
        List<ScriptElement> cmd = null;
        
        for(ScriptElement tmp : elememts){
            switch(tmp.getElementType()){
                case COMMAND :
                    if(cmd == null){
                        cmd = new LinkedList<>();
                        cmd.add(tmp);
                    }
                    break;
                case PARAMETER :
                    if(cmd != null){
                        cmd.add(tmp);
                    }
                    break;
                case COMMAD_END :
                    String cmdName;
                    if(cmd.get(0).getElementType() != ScriptElement.Type.COMMAND){
                        throw new InvalidTypeException("Failed to execute command, type of first ScriptElement is not COMMAND.");
                    }
                    cmdName = cmd.get(0).getContent();
                    AbstractCommand tmpCMD = console.getCommandStorage().findCommand(cmdName);
                    tmpCMD.
                    break;
            }
        }
        return "Not implemented yet.";
    }
}
