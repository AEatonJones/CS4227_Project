package reservation;

public class Reservation implements Visitable
{
    private String accountEmail;
    private String startDate;
    private int noNights;
    private float cost;
    private String location;
    private String roomID;
    
    private Reservation(String accountEmail, String startDate, int noNights,String location,String roomID)
    {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.location = location;
        this.roomID = roomID;
        this.cost = 0.0f;
    }
    
    private Reservation(String accountEmail, String startDate, int noNights, String location,String roomID,float cost) {
        this.accountEmail = accountEmail;
        this.startDate = startDate;
        this.noNights = noNights;
        this.location = location;
        this.roomID = roomID;
        this.cost = cost;
    }
    
    public static Reservation makeReservation(String accountEmail, String startDate, int noNights,String location,String roomID) {
        return new Reservation(accountEmail, startDate, noNights, location, roomID);
    }
    
    public static Reservation makeReservation(String accountEmail, String startDate, int noNights,String location,String roomID,float cost) {
        return new Reservation(accountEmail, startDate, noNights, location, roomID, cost);
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
        return location;
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
        return (accountEmail + "," + startDate + "," + noNights + "," + roomID + "," + location + "," + String.format("%.2f", cost));
    }

    @Override
    public void acceptRoomVisitor(Visitor visitor)
    {
        visitor.visit(this);
    }
}
