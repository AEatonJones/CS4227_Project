package hotel.room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoomControl {
    public static ArrayList<String> obtainRoomInfo(String location) throws IOException {
    ArrayList<String> room = new ArrayList<String>();
    String filepath = ".\\src\\resources\\rooms.txt";
    String details = null;
    BufferedReader reader = new BufferedReader(new FileReader(filepath));
    String line;
    while((line = reader.readLine()) != null) 
    {
        String [] roomDetails = line.split(",");
        if(roomDetails[1].equalsIgnoreCase(location)) {
            details = roomDetails[0] + "," +roomDetails[1] + "," +roomDetails[2] + "," + roomDetails[3]  + "," + roomDetails[4];
            room.add(details);
        }
    }
    return room;
    }
    
    public static Float getRoomCost(String location,String RoomID) throws IOException {
    String filepath = ".\\src\\resources\\rooms.txt";
    Float cost = 0.0f;
    BufferedReader reader = new BufferedReader(new FileReader(filepath));
    String line;
    boolean found = false;
    while((line = reader.readLine()) != null &&! found) 
    {
        String [] roomDetails = line.split(",");
        if(roomDetails[1].equalsIgnoreCase(location) && roomDetails[0].equalsIgnoreCase(RoomID)) {
            cost = Float.parseFloat(roomDetails[4]);
            found = true;
        }
    }
    return cost;
    }
}
