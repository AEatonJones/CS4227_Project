package hotel.room;

public abstract class Room 
{
    protected int id;
    protected int hotel_id;
    protected String description;
    protected float cost;
    
    public abstract int getID();
    
    public abstract int getHotelID();
    
    public abstract String getDescription();
    
    public abstract float getCost();
}
