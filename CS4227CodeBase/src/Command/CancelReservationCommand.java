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
    private Account account;
    
    public CancelReservationCommand(Reservation reservation, Account account)
    {
        this.reservation = reservation;
        this.account = account;
    }
    
    @Override
    public void execute()
    {
        
        File writeFile = new File("src/resources/reservations.txt");
        File tempFile = new File("src/resources/TempReservations.txt");
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(writeFile)); BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {
                String undo = reservation.toString().trim();
                String currentLine;
                while((currentLine = bufferedReader.readLine()) != null){
                    String trimmedLine = currentLine.trim();
                    if(!trimmedLine.equals(undo))
                        bufferedWriter.write(currentLine + "\n");
                }
            }
            writeFile.delete();
            tempFile.renameTo(writeFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(CancelReservationCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(CancelReservationCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void undo()
    {
        new MakeReservationCommand(reservation, account).execute();
    }
}