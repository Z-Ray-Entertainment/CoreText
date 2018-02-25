/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands.file;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class FileSystem extends AbstractCommand{
    private String currentDir = System.getProperty("user.home");

    public FileSystem() {
        super(new FileSystemDefinition());
    }

    @Override
    public String action(List<Parameter> params) {
        String fileName = prepareFileName(params.get(1).getValue());
        
        switch(params.get(0).getValue()){
            case "read" :
                return new FileReader().readFile(fileName);
            case "write" :
                return new FileWriter().writeFile(params.get(2).getValue(), fileName);
            case "list" :
                StringBuilder dirContent = new StringBuilder();
                File curDir = new File(currentDir);
                if(!curDir.isFile()){
                    for(String curItem: curDir.list()){
                        dirContent.append(curItem);
                        dirContent.append("\n");
                    }
                }
                
                return dirContent.toString();
        }
        return "";
    }
    
    private String prepareFileName(String fileName){
        String tmpName = fileName;
        
        if(fileName.startsWith("\"") && fileName.endsWith("\"")){
            tmpName = fileName.substring(1, fileName.length()-1);
        }
        if(tmpName.startsWith("~")){
            return System.getProperty("user.home")+fileName.substring(1);
        }
        
        return fileName;
    }
}
