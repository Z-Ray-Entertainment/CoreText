/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretex.commands.filesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Vortex Acherontic
 */
public class FileReader {
    public String readFile(String fileName){
        File file = new File(fileName);
        try{
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line = "", fileContent = "";
            line = reader.readLine();
            while(reader.readLine() != null){
                fileContent += line+"\n";
            }
            return fileContent;
        }
        catch (FileNotFoundException ex) {
            return "File not found. "+fileName;
        } catch (IOException ex) {
            return "Error while reading file: "+fileName+"\nException: "+ex.getMessage();
        }
    }
}
