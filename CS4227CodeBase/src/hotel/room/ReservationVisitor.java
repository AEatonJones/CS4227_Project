package hotel.room;

public class ReservationVisitor implements RoomVisitor {
    
    private int bedroomCount;
    private int gymCount;
    private int functionRoomCount;
    
    public ReservationVisitor(){
        bedroomCount = 0;
        gymCount = 0;
        functionRoomCount = 0;
    }

    @Override
    public void visit(FunctionRoom functionroom) {
        functionRoomCount++;
    }
    
    @Override
    public void visit(BedRoom bedroom) {
        bedroomCount++;
    }
    
    @Override 
    public void visit(Gym gym) {
        gymCount++;
    }
    
    public void visit(IRoom room){
        System.out.println("Booked:\n" + bedroomCount + " bedrooms\n" + gymCount + " gym passes\n" + functionRoomCount + " function rooms\n");
    }
    
}
