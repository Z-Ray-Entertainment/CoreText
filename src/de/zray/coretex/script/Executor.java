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
        List<ScriptElement> cmd = new LinkedList<>();
        List<ScriptElement> nested = new LinkedList<>();
        int openClips = 0;
        
        for(ScriptElement tmp : elememts){
            switch(tmp.getElementType()){
                case COMMAND :
                    if(openClips > 0){
                        nested.add(tmp);
                        System.out.println("[Executor]: Added to nested: "+tmp.getElementType()+" "+tmp.getContent());
                    } else {
                        cmd = new LinkedList<>();
                        cmd.add(tmp);
                        System.out.println("[Executor]: Added to cmd: "+tmp.getElementType()+" "+tmp.getContent());
                    }
                    break;
                case PARAMETER :
                case CODEBLOCK :
                    if(openClips > 0){
                        nested.add(tmp);
                        System.out.println("[Executor]: Added to nested: "+tmp.getElementType()+" "+tmp.getContent());
                    } else {
                        cmd.add(tmp);
                        System.out.println("[Executor]: Added to cmd: "+tmp.getElementType()+" "+tmp.getContent());
                    }
                    break;
                case COMMAD_END :
                    if(openClips > 0){
                        nested.add(tmp);
                    } else {
                        String cmdName;
                        /*if(cmd.get(0).getElementType() != ScriptElement.Type.COMMAND){
                        throw new InvalidTypeException("Failed to execute command, type of first ScriptElement is not COMMAND. "+cmd.get(0).getElementType().toString());
                        }*/
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
                        if(openClips == 1){
                            switch(tmp.getElementType()){
                                case PARAMETER :
                                    nested.add(new ScriptElement(ScriptElement.Type.COMMAND, tmp.getContent()));
                                    break;
                            }
                        } else {
                            nested.add(tmp);
                        }
                    }
                    break;
                case CLIP_CLOSE :
                    openClips--;
                    if(openClips == 0){
                        System.out.println("[Executor]: Executing nested commands!");
                        String nestedOut = new Executor(console).execute(nested);
                        ScriptElement nEl = new ScriptElement(ScriptElement.Type.PARAMETER, nestedOut);
                        cmd.add(nEl);
                        System.out.println("[Executor]: Added to cmd: "+nEl.getElementType()+" "+nEl.getContent());
                        //output.append(nestedOut);
                    } else if(openClips > 1){
                        nested.add(tmp);
                        System.out.println("[Executor]: Added to nested: "+tmp.getElementType()+" "+tmp.getContent());
                    }
                    break;
            }
        }
        
        if(!cmd.isEmpty()){
            System.out.println("[Executor]: Executed commands: ");
            for(ScriptElement el : cmd){
                System.out.println("-> "+el.getElementType()+" "+el.getContent());
            }
        }
        cmd.clear();
        nested.clear();
        return output.toString();
    }
}
