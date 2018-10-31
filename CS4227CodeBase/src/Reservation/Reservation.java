package Reservation;

import java.io.FileWriter;

public class Reservation 
{
    private static final String DBNAME = "src/resources/reservations.txt"; 
    
    private String accountID;
    private String startDate;
    private int noNights;
    private float cost;
    
    public Reservation(String accountID, String startDate, int noNights, float cost) {
        this.accountID = accountID;
        this.startDate = startDate;
        this.noNights = noNights;
        this.cost = cost;
    }
    
    public static Reservation makeReservation(String accountID, String startDate, int noNights, float cost) throws Exception {
        Reservation reservation = new Reservation(accountID, startDate, noNights, cost);
        
        FileWriter writer = new FileWriter(DBNAME, true);
        writer.append(reservation.toString());
        writer.close();
        
        return reservation;
    }
    
    public int getNoNights()
    {
        return noNights;
    }
    
    @Override
    public String toString()
    {
        return (accountID + "," + startDate + "," + noNights + "," + String.format("%.2f", cost) + "\n");
    }
}
