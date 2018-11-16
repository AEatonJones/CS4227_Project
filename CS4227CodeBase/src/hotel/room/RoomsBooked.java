package hotel.room;

public class RoomsBooked implements RoomVisitor{

    @Override
    public void visit(BedRoom bedroom) {
        System.out.println("Adding bedroom.");
    }
    
    @Override 
    public void visit(Gym gym) {
        System.out.println("Adding gym pass.");
    }
    
    @Override 
    public void visit(FunctionRoom functionRoom) {
        System.out.println("Adding function room.");
    }
}
