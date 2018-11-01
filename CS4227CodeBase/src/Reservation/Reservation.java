package Reservation;

import java.io.FileWriter;

public class Reservation 
{
    private static final String DBNAME = "src/resources/reservations.txt"; 
    
    private String accountEmail;
    private String startDate;
    private int noNights;
    private float cost;
    
    public Reservation(String accountEmail, String startDate, int noNights)
    {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.cost = 0.0f;
    }
    
    public Reservation(String accountEmail, String startDate, int noNights, float cost) {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.cost = cost;
    }
    
    public static Reservation makeReservation(String accountEmail, String startDate, int noNights, float cost) throws Exception {
        Reservation reservation = new Reservation(accountEmail, startDate, noNights, cost);
        
        FileWriter writer = new FileWriter(DBNAME, true);
        writer.append(reservation.toString());
        writer.close();
        
        return reservation;
    }
    
    public String getAccountEmail()
    {
        return accountEmail;
    }
    
    public int getNoNights()
    {
        return noNights;
    }
    
    public void setCost(float cost)
    {
        this.cost = cost;
    }
    
    @Override
    public String toString()
    {
        return (accountEmail + "," + startDate + "," + noNights + "," + String.format("%.2f", cost) + "\n");
    }
}
