/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex;

import de.zray.coretex.command.AbstractCommand;
import java.util.List;

/**
 *
 * @author vortex
 */
public class Configuration {
    private List<AbstractCommand> blackList;
    
    public boolean isBlackListed(AbstractCommand cmd){
        for(AbstractCommand cur : blackList){
            if(cur.getCMDName().equals(cmd.getCMDName())){
                return true;
            } else {
                for(String curAlias : cur.getAliases()){
                    for(String cmdAlias : cmd.getAliases()){
                        if(curAlias.equals(cmdAlias)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
