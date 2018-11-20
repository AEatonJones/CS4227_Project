package command;

import account.Account;
import reservation.Reservation;
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
    private Account account;
    
    public MakeReservationCommand(Reservation reservation, Account account)
    {
        this.reservation = reservation;
        this.account = account;
    }
    
    @Override
    public void execute()
    {
        //Calculate cost
        PaymentController controller = PaymentControllerFactory.getPaymentController(reservation);
        PaymentSystem system = PaymentSystemFactory.getPaymentSystem(controller, account);
        float cost = system.calculateCost(reservation);
        
        //Set cost
        reservation.setCost(cost);
        
        //Write reservation to file
        try(FileWriter filewriter = new FileWriter("src/resources/reservations.txt",true)){
            filewriter.write("\n" + reservation.toString());
        
        }   catch (IOException ex) {
            Logger.getLogger(MakeReservationCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void undo()
    {
        new CancelReservationCommand(reservation, account).execute();
    }
}
