package Reservation;

import java.io.FileWriter;

public class Reservation 
{
    private String accountEmail;
    private String startDate;
    private int noNights;
    private float cost;
    private String Location;
    private String roomID;
    
    private Reservation(String accountEmail, String startDate, int noNights,String location,String roomID)
    {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.Location = location;
        this.roomID = roomID;
        this.cost = 0.0f;
    }
    
    private Reservation(String accountEmail, String startDate, int noNights, String location,String roomID,float cost) {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.Location = location;
        this.roomID = roomID;
        this.cost = cost;
    }
    
    public static Reservation makeReservation(String accountEmail, String startDate, int noNights,String location,String roomID) throws Exception {
        Reservation reservation = new Reservation(accountEmail, startDate, noNights, location, roomID);
        return reservation;
    }
    
    public static Reservation makeReservation(String accountEmail, String startDate, int noNights,String location,String roomID,float cost) throws Exception {
        Reservation reservation = new Reservation(accountEmail, startDate, noNights, location, roomID, cost);
        return reservation;
    }
    
    public String getAccountEmail()
    {
        return accountEmail;
    }
    
    public String getStartDate()
    {
        return startDate;
    }
    
    public int getNoNights()
    {
        return noNights;
    }
    
    public String getLocation() {
        return Location;
    }
    
    public String getRoomID() {
        return roomID;
    }
    
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    
    public float getCost() {
        return cost;
    }
    
    public void setCost(float cost)
    {
        this.cost = cost;
    }
    
    @Override
    public String toString()
    {
        return (accountEmail + "," + startDate + "," + noNights + "," + roomID + "," + Location + "," + String.format("%.2f", cost));
    }
}
