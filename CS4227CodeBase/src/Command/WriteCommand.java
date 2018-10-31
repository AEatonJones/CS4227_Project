/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Reservation.Reservation;
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
    private Reservation reservation;
    
    public WriteCommand(String fileName, Reservation reservation){
        this.fileName = fileName;
        this.reservation = reservation;
        
    }

    @Override
    public void execute() {
        try(FileWriter filewriter = new FileWriter("src/resources/WriteCommandTest.txt",true)){
            filewriter.write("\n" + reservation.toString());
            filewriter.close();
        
    }   catch (IOException ex) {
            Logger.getLogger(WriteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void undo() {
        File writeFile = new File("src/resources/WriteCommandTest.txt");
        File tempFile = new File("src/resources/TempWriteCommandTest.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(writeFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
            String undo = reservation.toString().trim();
            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null){
                String trimmedLine = currentLine.trim();
                if(!trimmedLine.equals(undo))
                    bufferedWriter.write(currentLine + "\n");
            }
            bufferedWriter.close();
            bufferedReader.close();
            writeFile.delete();
            tempFile.renameTo(writeFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
    }
    
}
