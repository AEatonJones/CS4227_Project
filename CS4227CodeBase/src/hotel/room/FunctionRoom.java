package hotel.room;

public class FunctionRoom extends Room implements Visitable {
    
    private int days;
    
    public FunctionRoom(int id, String location, float cost) {
        super(id, location, cost);
    
    }

    @Override
    public void acceptRoomVisitor(RoomVisitor visitor) {
        visitor.visit(this);
    }
}
