package hotel.room;

public abstract class Room implements IRoom {
    protected int id;
    protected int hotel_id;
    protected String description;
    protected float cost;
    
    private BedRoom bedroom;
    
    @Override
    public int getID(){
        return id;
    }
    
    @Override
    public int getHotelID(){
        return hotel_id;
    }
    
    @Override
    public String getDescription(){
        return description;
    }
    
    @Override
    public float getCost(){
        return cost;
    }
    
    public Room(int id, int hotel_id, String description, float cost){
        this.id = id;
        this.hotel_id = hotel_id;
        this.description = description;
        this.cost = cost;
    }
    
    @Override
    public void acceptRoomVisitor(RoomVisitor visitor){
        bedroom.acceptRoomVisitor(visitor);
        visitor.visit((BedRoom)this);
        //!!
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
