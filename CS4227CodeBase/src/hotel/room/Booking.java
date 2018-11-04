package hotel.room;

public class Booking implements RoomVisitor {
    
    private int bedroomCount;
    
    public Booking(){
        bedroomCount = 0;
    }

    @Override
    public void visit(BedRoom bedroom) {
        bedroomCount++;
    }
    
    public void visit(IRoom room){
        System.out.println("Rooms booked:\n" + bedroomCount + "bedrooms\n");
    }
    
}
