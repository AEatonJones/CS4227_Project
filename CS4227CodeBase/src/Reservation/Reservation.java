package Reservation;

import java.io.FileWriter;

public class Reservation 
{
    private static final String DBNAME = "src/resources/reservations.txt"; 
    
    private String accountEmail;
    private String startDate;
    private int noNights;
    private float cost;
    private String Location;
    
    private Reservation(String accountEmail, String startDate, int noNights,String location)
    {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.Location = location;
        this.cost = 0.0f;
    }
    
    private Reservation(String accountEmail, String startDate, int noNights, String location, float cost) {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.Location = location;
        this.cost = cost;
    }
    
    public static Reservation makeReservation(String accountEmail, String startDate, int noNights,String location) throws Exception {
        Reservation reservation = new Reservation(accountEmail, startDate, noNights, location);
        return reservation;
    }
    
    public static Reservation makeReservation(String accountEmail, String startDate, int noNights,String location, float cost) throws Exception {
        Reservation reservation = new Reservation(accountEmail, startDate, noNights, location, cost);
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
    
    public String getLocation() {
        return Location;
    }
    
    public void setCost(float cost)
    {
        this.cost = cost;
    }
    
    @Override
    public String toString()
    {
        return (accountEmail + "," + startDate + "," + noNights + "," + Location + "," + String.format("%.2f", cost));
    }
}
