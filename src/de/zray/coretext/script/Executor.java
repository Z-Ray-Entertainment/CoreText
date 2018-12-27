/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.script;

import de.zray.coretext.Console;
import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.exceptions.InvalidParameterValueException;
import de.zray.coretext.exceptions.InvalidTypeException;
import de.zray.coretext.exceptions.ParameterAmountException;
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
        List<ScriptElement> cmd = new LinkedList<>();
        List<ScriptElement> nested = new LinkedList<>();
        int openClips = 0;
                        
        for(ScriptElement tmp : elememts){
            switch(tmp.getElementType()){
                case COMMAND :
                    if(openClips > 0){
                        nested.add(tmp);
                    } else {
                        cmd = new LinkedList<>();
                        cmd.add(tmp);
                    }
                    break;
                case PARAMETER :
                case CODEBLOCK :
                    if(openClips > 0){
                        nested.add(tmp);
                    } else {
                        cmd.add(tmp);
                    }
                    break;
                case COMMAD_END :
                    if(openClips > 0){
                        nested.add(tmp);
                    } else {
                        String cmdName;
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
                    break;
                case CLIP_OPEN :
                    openClips++;
                    if(openClips > 1){
                        nested.add(tmp);
                    }                    
                    break;
                case CLIP_CLOSE :
                    openClips--;
                    if(openClips > 0){
                        nested.add(tmp);
                    } else if (openClips == 0){
                        String nestedResult = new Executor(console).execute(nested);
                        cmd.add(new ScriptElement(ScriptElement.Type.PARAMETER, nestedResult));
                    }
                    break;
            }
        }
        cmd.clear();
        nested.clear();
        return output.toString();
    }
}
