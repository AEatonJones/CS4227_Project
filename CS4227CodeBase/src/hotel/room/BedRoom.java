package hotel.room;

public class BedRoom extends Room
{
    private int nights;

    public BedRoom(int id, String hotel_location, int no_of_single_beds, int no_of_double_beds, float cost) {
        super(id, hotel_location, no_of_single_beds, no_of_double_beds, cost);
    }
}
