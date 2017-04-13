/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex;

import de.zray.coretex.commands.Alias;
import de.zray.coretex.exceptions.UnknownVariableException;
import de.zray.coretex.commands.Echo;
import de.zray.coretex.commands.Help;
import de.zray.coretex.commands.Quit;
import de.zray.coretex.commands.variables.Var;
import de.zray.coretex.commands.algebra.Add;
import de.zray.coretex.commands.algebra.Div;
import de.zray.coretex.commands.algebra.Mod;
import de.zray.coretex.commands.algebra.Mult;
import de.zray.coretex.commands.algebra.Sub;
import de.zray.coretex.commands.bools.Equals;
import de.zray.coretex.commands.bools.If;
import de.zray.coretex.commands.bools.NotEqual;
import de.zray.coretex.commands.variables.ParseVarValue;
import de.zray.coretex.commands.variables.SetVariable;
import de.zray.coretex.exceptions.DublicateCommandException;
import de.zray.coretex.exceptions.InvalidParameterValueException;
import de.zray.coretex.exceptions.InvalidTypeException;
import de.zray.coretex.exceptions.ParameterAmountException;
import de.zray.coretex.exceptions.SyntaxException;
import java.util.Arrays;
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
        addCommand(new NotEqual());
        addCommand(new Alias());
        addCommand(new If());
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
    
    public String executeScript(String input) throws SyntaxException, ParameterAmountException, InvalidTypeException, InvalidParameterValueException{
        List<String> codeBlocks = buildCodeFragments(input);
        String outputs = "";
        for(String code : codeBlocks){
            List<String> commandBlocks = buildCommandBlocks(code);
            for(String command : commandBlocks){
                outputs += executeCommand(command);
            }
        }
        return outputs;
    }

    private List<String> buildCommandBlocks(String codeBlock){
        List<String> commandBlocks = new LinkedList<>();
        int clips = 0, comandBlockStart = 0;
        boolean string = false;
        for(int i = 0; i < codeBlock.length(); i++){
            switch(codeBlock.substring(i, i+1)){
                case ";" :
                case "\n" :
                    commandBlocks.add(codeBlock.substring(comandBlockStart, i));
                    comandBlockStart = i+1;
                    break;
                case "\"" :
                    string = !string;
                    break;
                case "(" :
                case "[" :
                    clips++;
                    break;
                case ")" :
                case "]" :
                    clips--;
                    break;
            }
        }
        return commandBlocks;
    }
    
    private List<String> buildCodeFragments(String input) throws SyntaxException{
        List<String> blocks = new LinkedList<>();
        boolean string = false;
        int start = 0, clips = 0;
        for(int i = 0; i < input.length(); i++){
            switch(input.substring(i, i+1)){
                case "\n":
                case ";" :
                    if(!string && clips == 0){
                        blocks.add(input.substring(start, i+1).trim());
                        System.out.println("CodeBlock: "+blocks.get(blocks.size()-1));
                        start = i+1;
                    }
                    break;
                case "\"" :
                    string = !string;
                    break;
                case "[" :
                case "(" :
                    clips++;
                    break;
                case "]" :
                case ")" :
                    clips--;
                    break;
            }
        }
        if(string || clips > 0){
            throw new SyntaxException("The given script is not valid, open strings: "+string+" open clips: "+clips);
        }
        return blocks;
    }
    
    public String executeCommand(String input) throws ParameterAmountException, InvalidTypeException, InvalidParameterValueException, SyntaxException {
        String output = "";
        List<String> validParameters = prepareInput(input);
        validParameters = buildCodeBlocks(validParameters);
        validParameters = buildStrings(validParameters);
        output += executeClips(validParameters)+"\n";
        return output;
    }
    
    private List<String> buildCodeBlocks(List<String> parameters){
        List<String> allWithBlocks = new LinkedList<>();
        boolean block= false;
        String codeBlock = "";
        for(String tmp : parameters){
            for(int i = 0; i < tmp.length(); i++){
                if(tmp.substring(i, i+1).equals("[") && !block){
                    block = true;
                }
                else if(tmp.substring(i, i+1).equals("]") && block){
                    block = false;
                    codeBlock += "]";
                    allWithBlocks.add(codeBlock);
                    codeBlock = "";
                }
                else if(block) {
                    codeBlock += tmp.substring(i, i+1);
                }
            }
            if(block && !codeBlock.isEmpty()){
                codeBlock += " ";
            }
            else if(!block && codeBlock.isEmpty()){
                allWithBlocks.add(tmp);
            }
        }
        
        return allWithBlocks;
    }
    
    private List<String> buildStrings(List<String> parameters){
        List<String> params = new LinkedList<>();
        
        boolean inString = false, inBlock = false;
        String string = "";
        for(String tmp : parameters){
            for(int i = 0; i < tmp.length(); i++){
                if(tmp.substring(i, i+1).equals("\"") && !inBlock && !inString){
                    inString = true;
                    i++;
                }
                else if(tmp.substring(i, i+1).equals("\"") && !inBlock && inString){
                    inString = false;
                }
                else if(tmp.substring(i, i+1).equals("[") && !inBlock && !inString){
                    inBlock = true;
                }
                else if(tmp.substring(i, i+1).equals("]") && inBlock && !inString){
                    inBlock = false;
                }
                if(inString){
                    string += tmp.substring(i, i+1);
                }
            }
            if(!inString && string.isEmpty()){
                params.add(tmp);
            }
            else if(!inString && !string.isEmpty()){
                params.add(string);
                string = "";
            }
            else{
                    string += " ";
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
    
    private String executeClips(List<String> params) throws SyntaxException, InvalidParameterValueException, ParameterAmountException{
        int firstClip = -1, lastClip = -1, clipCTC = 0;
        List<String> clipResults = new LinkedList<>();
        for(int i = 0; i < params.size(); i++){
            if(params.get(i).equals("(")){
                if(firstClip == -1){
                    firstClip = i;
                }
                clipCTC++;
            }
            else if(params.get(i).equals(")")){
                clipCTC--;
                if(clipCTC == 0){
                    lastClip = i;
                }
            }
            if(firstClip != -1 && lastClip != -1){
                clipResults.add(executeClips(params.subList(firstClip+1, lastClip)));
                firstClip = -1;
                lastClip = -1;
            }
            else if(firstClip == -1 && lastClip == -1){
                clipResults.add(params.get(i));
            }
        }
        return executeCommand(clipResults);
    }
    
    private List<String> prepareInput(String input){
        List<String> validTiles = new LinkedList<>();
        if(input != null && !input.isEmpty()){
            input = input.trim();
            String tiles[] = input.split(" ");
            validTiles = removeEmpty(tiles);
        }
        return validTiles;
    }
    
    private List<String> removeEmpty(String[] tiles){
        List<String> validTiles = new LinkedList<>();
        for(String tmp : tiles){
            if(!tmp.isEmpty() || !tmp.equals(" ")){
                validTiles.add(tmp.trim());
            }
        }
        return validTiles;
    }

    private AbstractCommand getMatchingCommand(String cmd){
        for(AbstractCommand tmp : cmds){
            if(tmp.getRootCMD().equals(cmd) || tmp.hasAlias(cmd)){
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
