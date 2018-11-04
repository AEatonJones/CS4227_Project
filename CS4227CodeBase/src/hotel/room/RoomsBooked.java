package hotel.room;

public class RoomsBooked implements RoomVisitor{

    @Override
    public void visit(BedRoom bedroom) {
        System.out.println("Adding bedroom.");
    }
    
}
