/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aaron
 */
public class AccountControl {
        /**
     * Verifies if users login exists and get there information if they do.
     * @param email Email of the profile which is logging in.
     * @param password Password for the profile which is logging in.
     * @return result if profile is there.
     * @throws IOException Throws IO Exception.
     */
    public static Account verifyAccount(String email,String password) throws IOException {
        Account result = null;
        String filepath;
        BufferedReader reader;
        String line;
        boolean found = false;

            filepath = ".\\src\\resources\\accounts.txt";
            reader = new BufferedReader(new FileReader(filepath));
            while(((line = reader.readLine()) != null) && !found) {
                String [] customerDetails = line.split(",");
                if(customerDetails[2].equalsIgnoreCase(email))
                {
                    if(customerDetails[3].equalsIgnoreCase(password))
                    {
                        found = true;
                        result = new Account.AccountBuilder(customerDetails[0],customerDetails[1],customerDetails[2],customerDetails[3],customerDetails[4]).build();
                    }
                }
            }
            
        return result;
    }
    
    /**
     * Removes selected employee from file if being promoted.
     * @param info Information on the profile.
     * @throws IOException Throws IO Exception.
     */
    public static void removeFromFile(String info) throws IOException {
        String line;
        File file = new File(".\\src\\resources\\accounts.txt");
        File tempFile = new File(file + ".tmp");
        //String filepath = ".\\src\\Resources\\Profiles\\Employees.txt";
        String finalFileContents = "";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while(((line = reader.readLine()) != null)) {
            if(!(line.equals(info))) {
                finalFileContents += line + "\n";
            }
        }
        reader.close();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        writer.write(finalFileContents);
        
        writer.close();
    }
    
    /**
     * Writes the info of employee which was promoted too a new file.
     * @param profile All the information of the profile.
     * @param type The type of the profile.
     * @throws IOException Throws IO Exception.
     */
    public static void printToFile(String[] profile) throws IOException {
        String filepath;
        FileWriter fr;
        
        filepath = ".\\src\\resources\\accounts.txt";
        fr = new FileWriter(filepath,true);
        try {
            fr.append("\n");
            for(int i = 0 ; i < profile.length; i++) {
                    fr.append(profile[i] + ",");
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        fr.close();
    }
    
    /**
     * Adds new Manager info to file and returns that info.
     * @param cafe Cafe object.
     * @return Employee
     * @throws IOException Throws IO EXception.
     */
    public static ArrayList<String> obtainReservationInfo(String cafe) throws IOException {
        ArrayList<String> Reservation = new ArrayList<String>();
        String filepath = ".\\src\\resources\\accounts.txt";
        String details = null;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while((line = reader.readLine()) != null) 
        {
            String [] accountDetails = line.split(",");
            if(accountDetails[5].equalsIgnoreCase(cafe)) {
                details = accountDetails[0] + " "+accountDetails[1] + " "+accountDetails[2] + " " + accountDetails[3] + " " + accountDetails[4];
                Reservation.add(details);
            }
        }
        
        return Reservation;
    }
    
}
