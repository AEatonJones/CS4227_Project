/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountControl {
    
    private static final String FILEPATH = ".\\src\\resources\\accounts.txt";
    
    private AccountControl() {
        //Made to avoid object creation
    }

    public static Account verifyAccount(String email,String password) throws IOException {
        Account result = null;
        BufferedReader reader;
        String line;
        boolean found = false;
            
        reader = new BufferedReader(new FileReader(FILEPATH));
        try {
            while(((line = reader.readLine()) != null) && !found) {
                String [] customerDetails = line.split(",");
                if(customerDetails[2].equalsIgnoreCase(email) && customerDetails[3].equalsIgnoreCase(password))
                {
                    found = true;
                    result = new Account.AccountBuilder(customerDetails[0],customerDetails[1],customerDetails[2],customerDetails[3],customerDetails[4]).build();
                }
            }
        } finally {
            reader.close();
        }  
        return result;
    }
    
    public static void printToFile(String[] profile) throws IOException {
        FileWriter fr;
        fr = new FileWriter(FILEPATH,true);
        try {
            fr.append("\n");
            for (String profile1 : profile) {
                fr.append(profile1 + ",");
            }
        } finally {
            fr.close();
        }
    }
    
    public static List<String> obtainReservationInfo(String cafe) throws IOException {
        ArrayList<String> reservation = new ArrayList<>();
        String details = null;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) {
            while((line = reader.readLine()) != null) 
            {
                String [] accountDetails = line.split(",");
                if(accountDetails[5].equalsIgnoreCase(cafe)) {
                    details = accountDetails[0] + " "+accountDetails[1] + " "+accountDetails[2] + " " + accountDetails[3] + " " + accountDetails[4];
                    reservation.add(details);
                }
            }
        }   
        return reservation;
    }  
}
