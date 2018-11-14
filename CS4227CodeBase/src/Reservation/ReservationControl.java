package Reservation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationControl {

    public static ArrayList<String> obtainReservationInfo(String email) throws IOException {
    ArrayList<String> res = new ArrayList<String>();
    String filepath = ".\\src\\resources\\reservations.txt";
    String details = null;
    BufferedReader reader = new BufferedReader(new FileReader(filepath));
    String line;
    while((line = reader.readLine()) != null) 
    {
        String [] resDetails = line.split(",");
        if(resDetails[0].equalsIgnoreCase(email)) {
            details = resDetails[0] + "," +resDetails[1] + "," +resDetails[2] + "," + resDetails[3]  + "," + resDetails[4] + "," + resDetails[5];
            res.add(details);
        }
    }
    return res;
    }
    
}
