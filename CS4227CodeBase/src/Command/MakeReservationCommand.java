package Command;

import Account.Account;
import Reservation.Reservation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import payment.PaymentController;
import payment.PaymentControllerFactory;
import payment.PaymentSystem;
import payment.PaymentSystemFactory;

public class MakeReservationCommand implements Command
{
    private Reservation reservation;
    private String location;
    private Account account;
    
    public MakeReservationCommand(Reservation reservation, String location, Account account)
    {
        this.reservation = reservation;
        this.location = location;
    }
    
    @Override
    public void execute()
    {
        //Calculate cost
        PaymentController controller = PaymentControllerFactory.getPaymentController(location);
        PaymentSystem system = PaymentSystemFactory.getPaymentSystem(controller, account);
        float cost = system.calculateCost(reservation);
        
        //Set cost
        reservation.setCost(cost);
        
        //Write reservation to file
        try(FileWriter filewriter = new FileWriter("src/resources/WriteCommandTest.txt",true)){
            filewriter.write("\n" + reservation.toString());
            filewriter.close();
        
        }   catch (IOException ex) {
            Logger.getLogger(WriteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void undo()
    {
        new CancelReservationCommand(reservation, location, account).execute();
    }
}
