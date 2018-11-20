package hotel.room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomControl {
    
    private static final String FILEPATH = ".\\src\\resources\\rooms.txt";
    
    private RoomControl() {
        //made to stop object creation
    }
    
    public static List<String> obtainRoomInfo(String location) throws IOException {
    ArrayList<String> room = new ArrayList<>();
    BufferedReader reader;
    String details = null;
    
    reader = new BufferedReader(new FileReader(FILEPATH));
    String line;
    try {
        while((line = reader.readLine()) != null) 
        {
            String [] roomDetails = line.split(",");
            if(roomDetails[1].equalsIgnoreCase(location)) {
                details = roomDetails[0] + "," +roomDetails[1] + "," +roomDetails[2] + "," + roomDetails[3]  + "," + roomDetails[4];
                room.add(details);
            }
        }
    } finally {
            reader.close();
    }
    return room;
    }
    
    public static Float getRoomCost(String location,String roomID) throws IOException {
    Float cost = 0.0f;
    BufferedReader reader;
    
    reader = new BufferedReader(new FileReader(FILEPATH));
    String line;
    boolean found = false;
    try {
        while((line = reader.readLine()) != null &&! found) 
        {
            String [] roomDetails = line.split(",");
            if(roomDetails[1].equalsIgnoreCase(location) && roomDetails[0].equalsIgnoreCase(roomID)) {
                cost = Float.parseFloat(roomDetails[4]);
                found = true;
            }
        }
    } finally {
            reader.close();
    }
    return cost;
    }
}
