/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hmaug
 */
public class WriteCommand implements Command {
    
    private String fileName;
    
    public WriteCommand(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try(FileWriter filewriter = new FileWriter("src/resources/WriteCommandTest.txt",true)){
            filewriter.write("\nwriting");
            filewriter.close();
        
    }   catch (IOException ex) {
            Logger.getLogger(WriteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void undo() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/WriteCommandTest.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/WriteCommandTest.txt"));
            String undo = "remove";
            String currentLine;
            while((currentLine = reader.readLine()) != null){
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(undo)) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            writer.close();
            reader.close();
            } catch (IOException ex) {
            Logger.getLogger(WriteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
    }
    
}
