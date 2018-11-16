package hotel.room;

public class Gym extends Room implements Visitable {
    
    private int days;
    
    public Gym(int id, String location, float cost) {
        super(id, location, cost);
    
    }

    @Override
    public void acceptRoomVisitor(RoomVisitor visitor) {
        visitor.visit(this);
    }
}
