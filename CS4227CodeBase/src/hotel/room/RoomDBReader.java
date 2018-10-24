package hotel.room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RoomDBReader 
{
    private int roomID;
    private String[] roomDetails;
    
    private final static String FILENAME = "./db/rooms.txt";
    
    public RoomDBReader(int roomID) throws FileNotFoundException
    {
        this.roomID = roomID;
        
        //Read file [.split()], put info into roomDetails
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        
    }
    
    public int getHotel_ID()
    {
        return Integer.parseInt(roomDetails[1]);
    }
    
    public String getDescription()
    {
        return roomDetails[2];
    }
    
    public float getCost()
    {
        return Float.parseFloat(roomDetails[3]);
    }
}
