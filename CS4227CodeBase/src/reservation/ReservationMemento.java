package reservation;
import account.Account;
import java.awt.event.ActionListener;

public class ReservationMemento {
    private final Reservation reservation;

    public ReservationMemento(Reservation reservation) {
        this.reservation = reservation;                  
    }
    
    public void restoreState(ReservationMemento reservation, Account account) {
        new ui.CustomerUI().restoreBooking(reservation,account);
    }
    
    public void restoreState(ReservationMemento reservation, Account account, ActionListener listener) {
        new ui.CustomerUI().restoreBooking(reservation,account, listener);
    }
    
    public Reservation getReservation() {
        return reservation;
    }
}
