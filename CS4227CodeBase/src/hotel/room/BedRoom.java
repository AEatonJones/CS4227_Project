package hotel.room;

import java.io.FileNotFoundException;

public class BedRoom extends Room
{
    private int nights;
    private RoomDBReader reader;
    
    public BedRoom(int id) throws FileNotFoundException
    {
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
    }
}
