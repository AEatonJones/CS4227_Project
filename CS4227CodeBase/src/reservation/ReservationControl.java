package reservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationControl {
    
    private static final String FILEPATH = ".\\src\\resources\\reservations.txt";
    
    private ReservationControl() {
        //made to stop object control
    }

    public static List<String> obtainReservationInfo(String email) throws IOException {
    ArrayList<String> res = new ArrayList<>();
    String details = null;
    String line;
    try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))){
        while((line = reader.readLine()) != null) 
        {
            String [] resDetails = line.split(",");
            if(resDetails[0].equalsIgnoreCase(email)) {
                details = resDetails[0] + "," +resDetails[1] + "," +resDetails[2] + "," + resDetails[3]  + "," + resDetails[4] + "," + resDetails[5];
                res.add(details);
            }
        }
    }    
    return res;
    }
    
}
