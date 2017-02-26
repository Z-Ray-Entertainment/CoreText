/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.se.script;

import de.zray.se.script.exceptions.UnknownVariableException;
import de.zray.se.script.commands.Echo;
import de.zray.se.script.commands.Help;
import de.zray.se.script.commands.Quit;
import de.zray.se.script.commands.variables.Var;
import de.zray.se.script.commands.algebra.Add;
import de.zray.se.script.commands.algebra.Div;
import de.zray.se.script.commands.algebra.Mod;
import de.zray.se.script.commands.algebra.Mult;
import de.zray.se.script.commands.algebra.Sub;
import de.zray.se.script.commands.bools.Equals;
import de.zray.se.script.commands.variables.ParseVarValue;
import de.zray.se.script.commands.variables.SetVariable;
import de.zray.se.script.exceptions.DublicateCommandException;
import de.zray.se.script.exceptions.InvalidParameterValueException;
import de.zray.se.script.exceptions.InvalidTypeException;
import de.zray.se.script.exceptions.ParameterAmountException;
import de.zray.se.script.exceptions.SyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Console {
    private List<AbstractCommand> cmds = new LinkedList<>();
    private VariableStore varStore;
    
    public Console() throws DublicateCommandException{
        this.varStore = new VariableStore();
        initDefaultCMDs();
    }
    
    private void initDefaultCMDs() throws DublicateCommandException{
        addCommand(new Quit());
        addCommand(new Echo());
        addCommand(new Help());
        addCommand(new Add());
        addCommand(new Sub());
        addCommand(new Mult());
        addCommand(new Div());
        addCommand(new Mod());
        addCommand(new Var());
        addCommand(new ParseVarValue());
        addCommand(new SetVariable());
        addCommand(new Equals());
    }
    
    public void addCommand(AbstractCommand cmd) throws DublicateCommandException{
        for(AbstractCommand tmp : cmds){
            if(tmp.getRootCMD().equals(cmd.getRootCMD())){
                throw new  DublicateCommandException(cmd);
            }
        }
        cmd.setConsole(this);
        cmds.add(cmd);
    }
    
    public List<AbstractCommand> getCommands(){
        return cmds;
    }
    
    public String executeCommand(String input) throws ParameterAmountException, InvalidTypeException, InvalidParameterValueException, SyntaxException {
        List<String> validParameters = prepareInput(input);
        validParameters = buildStrings(validParameters);
        return executeCommand(validParameters);
    }
    
    private List<String> buildStrings(List<String> parameters){
        List<String> params = new LinkedList<>();
        
        boolean inString = false;
        String cmd = "";
        for(String tmp : parameters){
            if(tmp.startsWith("\"")){
                inString = true;
                tmp = tmp.substring(1);
            }
            if(inString){
                cmd += tmp+" ";
            }
            else{
                params.add(tmp);
            }
            if(tmp.endsWith("\"")){
                inString = false;
                cmd = cmd.substring(0, cmd.length()-2);
                params.add(cmd);
            }
        }
        return params;
    }
    
    private String executeCommand(List<String> validParameters) throws InvalidParameterValueException, ParameterAmountException{
        AbstractCommand cmd = getMatchingCommand(validParameters.get(0));
        if(cmd != null){
            if(cmd.requireParameters()){
                return cmd.execute(cmd.buildParameters(validParameters.subList(1, validParameters.size())));
            }
            else{
                return cmd.execute(null);
            }
        }
        return validParameters.get(0)+" not found, tipe help all to see all commands.";
    }
    
    private List<String> executeClips(List<String> params) throws SyntaxException{
        int firstClip = -1, lastClip = -1;
        for(int i = 0; i < params.size(); i++){
            if(params.get(i).equals("(") && firstClip == -1){
                firstClip = i;
            }
            else if(params.get(i).equals(")")){
                lastClip = i;
            }
        }
        if(firstClip != -1 && lastClip != -1){
            return params.subList(firstClip, lastClip);
        }
        else if(firstClip == -1 && lastClip == -1){
            return params;
        }
        else{
            throw new SyntaxException("The amount of ( and ) is invlaid");
        }
    }
    
    private List<String> prepareInput(String input){
        List<String> validTiles = new LinkedList<>();
        if(input != null && !input.isEmpty()){
            input = input.replace("\t", "");
            String tiles[] = input.split(" ");
            validTiles = removeEmpty(tiles);
        }
        return validTiles;
    }
    
    private List<String> removeEmpty(String[] tiles){
        List<String> validTiles = new LinkedList<>();
        for(String tmp : tiles){
            if(!tmp.isEmpty() || !tmp.equals(" ")){
                validTiles.add(tmp);
            }
        }
        return validTiles;
    }

    private AbstractCommand getMatchingCommand(String cmd){
        for(AbstractCommand tmp : cmds){
            if(tmp.getRootCMD().equals(cmd)){
                return tmp;
            }
        }
        return null;
    }
    
    public void createVar(Variable var){
        varStore.addVariable(var);
    }
    
    public Variable getVariable(String name) throws UnknownVariableException{
        return varStore.getVariable(name);
    }
}
