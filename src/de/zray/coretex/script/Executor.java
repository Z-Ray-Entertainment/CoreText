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
    private StringBuilder output;
    
    public Executor(Console console){
        this.console = console;
    }
    
    public String execute(List<ScriptElement> elememts) throws InvalidTypeException, InvalidParameterValueException, ParameterAmountException{
        output = new StringBuilder();
        List<ScriptElement> cmd = null;
        boolean readingString = false;
        String string = "";
        
        for(ScriptElement tmp : elememts){
            switch(tmp.getElementType()){
                case COMMAND :
                    if(cmd == null){
                        cmd = new LinkedList<>();
                        cmd.add(tmp);
                    }
                    break;
                case STRING_CHARACTER :
                    readingString = !readingString;
                    if(readingString){
                        string = "";
                    } else{
                        if(cmd != null){
                            cmd.add(new ScriptElement(ScriptElement.Type.PARAMETER, string.substring(0, string.length()-1)));
                        }
                    }
                    break;
                case PARAMETER :
                    if(readingString){
                        string += tmp.getContent()+" ";
                    } else {
                        if(cmd != null){
                            cmd.add(tmp);
                        }
                    }
                    
                    break;
                case COMMAD_END :
                    String cmdName;
                    if(cmd.get(0).getElementType() != ScriptElement.Type.COMMAND){
                        throw new InvalidTypeException("Failed to execute command, type of first ScriptElement is not COMMAND.");
                    }
                    cmdName = cmd.get(0).getContent();
                    AbstractCommand tmpCMD = console.getCommandStorage().findCommand(cmdName);
                    if(tmpCMD != null){
                        if(!(cmd.size() <= 1)){
                            List<ScriptElement> parameters = cmd.subList(1, cmd.size());
                                output.append(tmpCMD.execute(tmpCMD.buildParameters(parameters)));
                        }
                        else {
                            output.append(tmpCMD.execute(null));
                        }
                    }
                    else{
                        output.append("Command ").append(cmdName).append(" not found");
                    }
            }
        }
        return output.toString();
    }
}
