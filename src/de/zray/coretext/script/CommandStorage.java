/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.script;

import de.zray.coretext.Console;
import de.zray.coretext.command.AbstractCommand;
import de.zray.coretext.defaults.commands.Alias;
import de.zray.coretext.defaults.commands.Coretex;
import de.zray.coretext.defaults.commands.Echo;
import de.zray.coretext.defaults.commands.Help;
import de.zray.coretext.defaults.commands.bool.If;
import de.zray.coretext.defaults.commands.Quit;
import de.zray.coretext.defaults.commands.algebra.Add;
import de.zray.coretext.defaults.commands.algebra.Divide;
import de.zray.coretext.defaults.commands.algebra.Modulo;
import de.zray.coretext.defaults.commands.algebra.Multiplication;
import de.zray.coretext.defaults.commands.algebra.Substract;
import de.zray.coretext.defaults.commands.bool.Equals;
import de.zray.coretext.defaults.commands.bool.NotEqual;
import de.zray.coretext.defaults.commands.file.FileSystem;
import de.zray.coretext.defaults.commands.variables.ParseVarValue;
import de.zray.coretext.defaults.commands.variables.SetVariable;
import de.zray.coretext.defaults.commands.variables.Var;
import de.zray.coretext.exceptions.AliasException;
import de.zray.coretext.exceptions.DublicateCommandException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class CommandStorage {
    private List<AbstractCommand> cmds = new LinkedList<>();
    private Console console;
    
    public CommandStorage(Console console, boolean defaultCMDs) throws DublicateCommandException, AliasException{
        this.console = console;
        addCommand(new Coretex());
        if(defaultCMDs){
            initDefaultCMDs();
        }
    }
    
    private void initDefaultCMDs() throws DublicateCommandException, AliasException{
        addCommand(new Quit());
        addCommand(new Echo());
        addCommand(new Help());
        addCommand(new Add());
        addCommand(new Substract());
        addCommand(new Multiplication());
        addCommand(new Divide());
        addCommand(new Modulo());
        addCommand(new Var());
        addCommand(new ParseVarValue());
        addCommand(new SetVariable());
        addCommand(new Equals());
        addCommand(new NotEqual());
        addCommand(new Alias());
        addCommand(new If());
        addCommand(new FileSystem());
    }
    
     public void addCommand(AbstractCommand cmd) throws DublicateCommandException{
        for(AbstractCommand tmp : cmds){
            if(tmp.matchesName(cmd.getCMDName())){
                throw new  DublicateCommandException(cmd);
            }
        }
        cmd.setConsole(console);
        cmds.add(cmd);
    }
     
    public AbstractCommand findCommand(String cmdName){
        for(AbstractCommand cmd : cmds){
            if(cmd.getCMDName().equals(cmdName)){
                return cmd;
            }
            if(cmd.hasAlias(cmdName)){
                return cmd;
            }
        }
        return null;
    }
    
    public List<AbstractCommand> getCommands(){
        return cmds;
    }
}
