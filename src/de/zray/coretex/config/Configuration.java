/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.config;

/**
 *
 * @author vortex
 */
public class Configuration {
    private static Configuration config;
    private Blacklist blackList;
    private int historySize = 10;
    
    public Configuration(){
        blackList = new Blacklist();
    }
    
    public static Configuration getConfig(){
        if(config == null){
            config = new Configuration();
        }
        return config;
    }
    
    public Blacklist getBlacklist(){
        return blackList;
    }
    
    public int getHistorySize(){
        return historySize;
    }
    
    public void setHistorySize(int size){
        historySize = size;
    }
}
