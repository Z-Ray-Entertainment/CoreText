/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.script;

import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Executor {
    public String execute(List<ScriptElement> elememts){
        for(ScriptElement tmp : elememts){
            switch(tmp.getElementType()){
                case COMMAND :
                    break;
                case PARAMETER :
                    break;
            }
        }
        return "Not implemented yet.";
    }
}
