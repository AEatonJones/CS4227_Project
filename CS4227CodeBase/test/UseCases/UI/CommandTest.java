/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases.UI;

import Command.*;
import java.io.BufferedReader;
import java.io.FileReader;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hmaug
 */
public class CommandTest {
    
    @Test
    public void testCommandTest() {
        Command cmd = new TestCommand();
        cmd.execute();
        cmd.undo();
    }
    
    @Test
    public void TestCommandWrite() {
        try {
            Command cmd = new WriteCommand("src/resources/WriteCommandTest.txt");
            
            cmd.execute();
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/WriteCommandTest.txt"));
            assertEquals("Test1", reader.readLine());
            assertEquals("writing", reader.readLine()); //In writeCommand, have it write to file the string on the line
            reader.close();
            
            cmd.undo();
            
            while((reader.readLine()) != null){
                assertEquals(false,reader.readLine().equals("writing"));
            }
            
            reader.close();
            //New reader, loop until end of file, assertEquals(false, reader.readLine().equals("WriteCommand writing"));, 
            //reader.close()
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }
}