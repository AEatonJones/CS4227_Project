package hotel.room;

public interface IRoom extends Visitable {
    
    public int getID();
    public int getHotelID();
    public String getDescription();
    public float getCost();
    
    public void acceptRoomVisitor(RoomVisitor visitor);
}
