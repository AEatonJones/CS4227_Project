package hotel.room;

public class Booking implements RoomVisitor {
    
    private int bedroomCount;
    private int gymCount;
    
    public Booking(){
        bedroomCount = 0;
        gymCount = 0;
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
        System.out.println("Booked:\n" + bedroomCount + " bedrooms\n" + gymCount + " gym passes\n");
    }
    
}
