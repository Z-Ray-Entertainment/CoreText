/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.defaults.commands.file;

import de.zray.coretex.command.AbstractCommand;
import de.zray.coretex.command.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class FileSystem extends AbstractCommand{

    public FileSystem() {
        super("file", null);
        Parameter param1 = new Parameter(Parameter.Type.STRING); //Action
        Parameter param2 = new Parameter(Parameter.Type.STRING); //File
        List<Parameter> set = new LinkedList<>();
        set.add(param1);
        set.add(param2);
        addParameters(set);
        Parameter param3 = new Parameter(Parameter.Type.STRING); //Content
        List<Parameter> set2 = new LinkedList<>();
        set2.add(param1);
        set2.add(param2);
        set2.add(param3);
        addParameters(set2);
    }

    @Override
    public String action(List<Parameter> params) {
        String fileName = prepareFileName(params.get(1).getValue());
        
        switch(params.get(0).getValue()){
            case "read" :
                return new FileReader().readFile(fileName);
            case "write" :
                return new FileWriter().writeFile(params.get(2).getValue(), fileName);
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
