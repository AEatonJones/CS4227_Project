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
import Reservation.ReservationMemento;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CommandLineInterface implements UI {

    Reservation reservation = null;
    Account currentAccount = null;
    Scanner in = new Scanner(System.in);
    @Override
    public void drawSignIn() {
        
        System.out.println("PLEASE NETER EMAIL, OR PLEASE ENTER LOGOUT TO LOGOUT");
        String potentialEmail = in.nextLine();
        if(potentialEmail.equals("LOGOUT")){
            drawLogIn();
        }
        System.out.println("PLEASE ENTER PASSWORD.");
        String potentialPassword = in.nextLine();
        try {
            currentAccount = AccountControl.verifyAccount(potentialEmail,potentialPassword);
        } catch (IOException ex) {
            Logger.getLogger(CommandLineInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
                    if(currentAccount != null) {
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
       System.out.println("WELCOME! 1)SIGN IN 2)REGISTER 3)QUIT");
       int input = Integer.parseInt(in.nextLine());
       switch(input){
           case 1: drawSignIn(); break;
           case 2: drawRegister(); break;
           case 3: System.out.println("GOODBYE"); break;
           }
      
       
    }

    @Override
    public void drawMainMenu() {
       System.out.println("1)MAKE RESERVATION 2)VIEW RESERVATION 3)SIGN OUT 4)QUIT");
       int input = Integer.parseInt(in.nextLine());
       switch(input){
           case 1: drawMakeReservations(); break;
           case 2: drawViewReservations(); break;
           case 3: drawLogIn(); break;
           case 4: System.out.println("GOODBYE"); break;
           }
    }

    @Override
    public void drawViewReservations() {
        System.out.println("1)CANCEL RESERVATION 2)BACK");
        int input = Integer.parseInt(in.nextLine());
        switch(input){
           case 1: System.out.println("NOT SUPPORTED."); break;
           case 2: drawMainMenu(); break;
           }
    }

    @Override
    public void drawMakeReservations() {
        System.out.println("PLEASE ENTER CHECK IN DATE, OR PLEASE ENTER BACK TO GO BACK.");
        String checkInDate = in.nextLine();
        if(checkInDate.equals("BACK")){
            drawMainMenu();
        }
        else
                try {
                    System.out.println("PLEASE ENTER CHECK IN DATE.");
                    String cID = in.nextLine();
                    System.out.println("PLEASE ENTER NUMBER OF NIGHTS");
                    int nON = Integer.parseInt(in.nextLine());
                    System.out.println("PLEASE ENTER LOCATION");
                    String location = in.nextLine();

                    Reservation r = Reservation.makeReservation(currentAccount.getEmail(), cID, nON, location,"");
                    drawAvailableRooms();
                } catch (Exception ex) {
                    Logger.getLogger(CustomerUI.CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
    } 

    @Override
    public void drawAvailableRooms() {
        System.out.println("PLEASE SELECT A ROOM PLEASE ENTER ROOM ID 2)BACK");
        int input = Integer.parseInt(in.nextLine());
        String room = null;
        if(input == 2){
           drawMakeReservations();
           }
        else 
           
       if(!(input == 2)){
           System.out.println("CAN YOU CONFIRM RESERVATION 1)YES 2)NO");
           input = Integer.parseInt(in.nextLine());
           if(input == 1){
           new MakeReservationCommand(reservation, currentAccount).execute();
           }
           else
              new CancelReservationCommand(reservation, currentAccount).execute(); 
       }
    }

}
