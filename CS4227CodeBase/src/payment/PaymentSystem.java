package payment;

import reservation.Reservation;
import hotel.room.RoomControl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PaymentSystem
{
    private PaymentController payment;
    
    public PaymentSystem(PaymentController payment)
    {
        this.payment = payment;
    }
    
    public float calculateCost(Reservation reservation)
    {
        float roomCost = 0.0f;
        try {
            roomCost = RoomControl.getRoomCost(reservation.getLocation(), reservation.getRoomID());
        } catch (IOException ex) {
            Logger.getLogger(PaymentSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (payment.getCostPlusTax(roomCost) * reservation.getNoNights());
    }
}
