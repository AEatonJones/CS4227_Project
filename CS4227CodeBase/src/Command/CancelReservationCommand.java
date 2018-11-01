package Command;

import Account.Account;
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

public class CancelReservationCommand implements Command
{
    private Reservation reservation;
    private String location;
    private Account account;
    
    public CancelReservationCommand(Reservation reservation, String location, Account account)
    {
        this.reservation = reservation;
        this.location = location;
        this.account = account;
    }
    
    @Override
    public void execute()
    {
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

    @Override
    public void undo()
    {
        new MakeReservationCommand(reservation, location, account).execute();
    }
}
