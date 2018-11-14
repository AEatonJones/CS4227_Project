package Reservation;
import Account.Account;
import UI.CustomerUI;

public class ReservationMemento {
    public Reservation reservation;
    private Account account;
    
    private String copyOfAccountEmail;
    private String copyOfStartDate;
    private int copyOfNoNights;
    private float copyOfCost;
    private String copyOfLocation;
    private String copyOfRoomID;
    
    public ReservationMemento(Reservation reservation,Account account) {
        this.reservation = reservation;
        this.account = account;
        
        this.copyOfAccountEmail = reservation.getAccountEmail();
        this.copyOfStartDate = reservation.getStartDate();
        this.copyOfNoNights = reservation.getNoNights();
        this.copyOfCost = reservation.getCost();
        this.copyOfRoomID = reservation.getRoomID();
        this.copyOfLocation = reservation.getLocation();
                        
    }
    
    public void restoreState(ReservationMemento reservation, Account account) {
        new UI.CustomerUI().restoreBooking(reservation,account);
    }
}
