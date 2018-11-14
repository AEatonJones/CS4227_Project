package hotel.room;

public interface IRoom extends Visitable {
    
    public int getID();
    public String getHotelLocation();
    public int getSingleBeds();
    public int getDoubleBeds();
    public float getCost();
    
    public void acceptRoomVisitor(RoomVisitor visitor);
}
