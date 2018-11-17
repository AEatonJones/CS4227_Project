/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author hmaug
 */
import Account.Account;
import Account.AccountControl;
import Command.*;
import Reservation.Reservation;
import interceptor.Dispatcher;
import interceptor.Interceptor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CommandLineInterface implements UI {
    
    public CommandLineInterface(){
        drawLogIn();
    }

    String email;
    Reservation reservation = null;
    Account currentAccount = null;
    Scanner in = new Scanner(System.in);
    @Override
    public void drawSignIn() {
        
        System.out.println("PLEASE ENTER EMAIL.");
        String potentialEmail = in.nextLine();
        email = potentialEmail;

        System.out.println("PLEASE ENTER PASSWORD.");
        String potentialPassword = in.nextLine();
        try {
            currentAccount = AccountControl.verifyAccount(potentialEmail,potentialPassword);
        } catch (IOException ex) {
            Logger.getLogger(CommandLineInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
                    if(currentAccount != null) {
                        Dispatcher dispatcher = Dispatcher.getInstance();
                        dispatcher.setCurrentInterceptor("Login", currentAccount);
                        Interceptor interceptor = dispatcher.dispatch();
                        interceptor.logger(currentAccount);
                        drawMainMenu();
                    }
                    else {
                        System.out.println("Invalid Information given!");
                    }
    }

    @Override
    public void drawRegister() {
        System.out.println("NOT SUPPORTED YET");
        drawLogIn();
    }

    @Override
    public void drawLogIn() {
       System.out.println("WELCOME! 1)SIGN IN 2)REGISTER 3)QUIT 4)ADD MOD");
       int input = Integer.parseInt(in.nextLine());
       switch(input){
           case 1: drawSignIn(); break;
           case 2: drawRegister(); break;
           case 3: System.out.println("GOODBYE");
                   Dispatcher dispatcher = Dispatcher.getInstance();
                   dispatcher.setCurrentInterceptor("Logout", currentAccount);
                   Interceptor interceptor = dispatcher.dispatch();
                   interceptor.logger(currentAccount);
                   break;
           case 4: drawModMenu(); break;
           }
    }
     
     public void drawModMenu(){
         System.out.println("i hate/love you jack");
     }
      
       
    

    @Override
    public void drawMainMenu() {
       System.out.println("1)MAKE RESERVATION 2)VIEW RESERVATION 3)SIGN OUT 4)QUIT");
       int input = Integer.parseInt(in.nextLine());
       switch(input){
           case 1: drawMakeReservations(); break;
           case 2: drawViewReservations(); break;
           case 3: drawLogIn(); break;
           case 4: System.out.println("GOODBYE"); 
                   System.exit(0); 
                   Dispatcher dispatcher = Dispatcher.getInstance();
                   dispatcher.setCurrentInterceptor("Logout", currentAccount);
                   Interceptor interceptor = dispatcher.dispatch();
                   interceptor.logger(currentAccount);
                   break;
           }
    }

    @Override
    public void drawViewReservations() {
        
        
        String line;
        BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader("src/resources/reservations.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CommandLineInterface.class.getName()).log(Level.SEVERE, null, ex);
            }             
        try {
            while ((line = bufferedReader.readLine()) != null) {
            if(line.startsWith(email.trim())){
                System.out.println(line);
            }
        }
        System.out.println("1)CANCEL RESERVATION 2)BACK");
        String input = in.nextLine();
        if(input.equals("1")){
        
                System.out.println("PLEASE ENTER RESERVATION TO BE CANCELLED IN CORRECT FORMAT. 2)BACK");
                input = in.nextLine();
                if(input.equals("2")){
                    drawMainMenu();
                }
                else{
                    
                }
                }
        } catch (IOException ex) {
            Logger.getLogger(CommandLineInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
drawMainMenu();
          
    }

    @Override
    public void drawMakeReservations() {
        System.out.println("PLEASE ENTER CHECK IN DATE, OR PLEASE ENTER BACK TO GO BACK.");
        String cID = in.nextLine();
        if(cID.equals("BACK")){
            drawMainMenu();
        }
        else
                try {
                    System.out.println("PLEASE ENTER NUMBER OF NIGHTS");
                    int nON = Integer.parseInt(in.nextLine());
                    System.out.println("PLEASE ENTER LOCATION IS CASE SENSITIVE.");
                    String location = in.nextLine();

                    reservation = Reservation.makeReservation(currentAccount.getEmail(), cID, nON, location,"");
                    drawAvailableRooms();
                } catch (Exception ex) {
                    Logger.getLogger(CustomerUI.CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
    } 

    @Override
    public void drawAvailableRooms() {
        System.out.println("PLEASE SELECT A ROOM PLEASE ENTER ROOM ID 2)BACK");
        String input = in.nextLine();
        String room = null;
        if(input.equals("2")){
           drawMakeReservations();
           }
        else {
            try {
                String line;
                BufferedReader bufferedReader = new BufferedReader(new FileReader("src/resources/rooms.txt"));
                while ((line = bufferedReader.readLine()) != null) {
                if(line.startsWith(input.trim())){
                    System.out.println(line + " CAN YOU CONFIRM CORRECT ROOM 1)YES 2)NO.");
                    input = in.nextLine();
                    if(input.equals("1")){
                         System.out.println("CAN YOU CONFIRM RESERVATION 1)YES 2)NO");
                         input = in.nextLine();
                         if(input.equals("1")){
                         new MakeReservationCommand(reservation, currentAccount).execute();
                         drawMainMenu();
                         }
                         else
                          new CancelReservationCommand(reservation, currentAccount).execute(); 
                         drawMainMenu();
                        }
                    }
                drawAvailableRooms();
                }
                
                bufferedReader.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(CommandLineInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            } 
    }

      

}
