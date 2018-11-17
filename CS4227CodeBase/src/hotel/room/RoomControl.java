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
    
    public static ArrayList<BedRoom> roomsReserved() throws IOException {
    ArrayList<BedRoom> room = new ArrayList<BedRoom>();
    String filepath = ".\\src\\resources\\reservations.txt";
    String anotherFilepath = ".\\src\\resources\\rooms.txt";
    BufferedReader reader = new BufferedReader(new FileReader(filepath));
    String line;
    while((line = reader.readLine()) != null) 
    {
        String [] resDetails = line.split(",");
        String roomID = resDetails[3];
        String location = resDetails[4];
        BufferedReader anotherReader = new BufferedReader(new FileReader(anotherFilepath));
        while((line = reader.readLine()) != null)
        {
            String [] roomDetails = line.split(",");
            BedRoom aRoom = null;
            if(roomDetails[0] == roomID && roomDetails[1].equalsIgnoreCase(location))
            aRoom = new BedRoom(Integer.parseInt(roomDetails[0]), roomDetails[1], Integer.parseInt(roomDetails[2]), Integer.parseInt(roomDetails[3]), Integer.parseInt(resDetails[5]));
            room.add(aRoom);
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
