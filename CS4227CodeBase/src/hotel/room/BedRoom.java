package hotel.room;

//import java.io.FileNotFoundException;

public class BedRoom extends Room implements Visitable
{
    private int nights;
    /*private RoomDBReader reader;
    
    public BedRoom(int id) throws FileNotFoundException {
        this.id = id;
        reader = new RoomDBReader(id);
    }
    
    @Override
    public float getCost()
    {
        return reader.getCost() * nights;
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public int getHotelID()
    {
        return reader.getHotel_ID();
    }

    @Override
    public String getDescription()
    {
        return reader.getDescription();
    }*/

    public BedRoom(int id, int hotel_id, String description, float cost) {
        super(id, hotel_id, description, cost);
    }

    @Override
    public void acceptRoomVisitor(RoomVisitor visitor) {
        visitor.visit(this);
    }
}
