package hotel.room;

//import java.io.FileNotFoundException;

public class BedRoom extends Room implements Visitable
{
    private int nights;

    public BedRoom(int id, String hotel_location, int no_of_single_beds, int no_of_double_beds, float cost) {
        super(id, hotel_location, no_of_single_beds, no_of_double_beds, cost);
    }

    @Override
    public void acceptRoomVisitor(RoomVisitor visitor) {
        visitor.visit(this);
    }
}
