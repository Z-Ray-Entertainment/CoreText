/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.config;

import de.zray.coretex.command.AbstractCommand;
import java.util.List;

/**
 *
 * @author vortex
 */
public class Blacklist {
    private List<String> blackList;
    
    public boolean isBlackListed(AbstractCommand cmd){
        for(String cur : blackList){
            if(cur.equals(cmd.getCMDName())){
                return true;
            } else {
                if (cmd.getAliases().stream().anyMatch((alias) -> (cur.equals(alias)))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void addToBlackList(String cmdNameOrAlias){
        if(isAlreadyBlacklisted(cmdNameOrAlias)){
            blackList.add(cmdNameOrAlias);
        }
    }
    
    private boolean isAlreadyBlacklisted(String test){
        return blackList.stream().anyMatch((cur) -> (cur.equals(test)));
    }
}
