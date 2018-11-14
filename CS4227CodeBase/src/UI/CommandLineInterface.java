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
import Command.*;
import java.util.*;
public class CommandLineInterface implements UI {

    Scanner in = new Scanner(System.in);
    @Override
    public void drawSignIn() {
        
        System.out.println("PLEASE NETER USERNAME, OR PLEASE ETER LOGOUT TO LOGOUT");
        String username = in.nextLine();
        if(username.equals("LOGOUT")){
            drawLogIn();
        }
        System.out.println("PLEASE ENTER PASSWORD.");
        String password = in.nextLine();
        drawMainMenu();
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
        System.out.println("PLEASE ENTER NUMBER OF NIGHTS.");
        String numberOfNights = in.nextLine();
        System.out.println("PLEASE ENTER A LOCATION.");
        String location = in.nextLine();
        drawAvailableRooms();
    }

    @Override
    public void drawAvailableRooms() {
        System.out.println("PLEASE SELECT A ROOM 1)ROOM101 2)R0OM102 3) ROOM103 4)BACK");
        int input = Integer.parseInt(in.nextLine());
        String room = null;
       switch(input){
           case 1: room = "101,London,1,0,25"; break;
           case 2: room = "102,London,2,1,100"; break;
           case 3: room = "103,London,1,0,25"; break;
           case 4: drawMakeReservations(); break;
           }
       if(!(room.equals("GOODBYE"))){
           System.out.println("CAN YOU CONFIRM RESERVATION 1)YES 2)NO");
           input = Integer.parseInt(in.nextLine());
           if(input == 1){
           new MakeReservationCommand(reservation, account).execute();
           }
           else
              new CancelReservationCommand(reservation, account).execute(); 
       }
    }
    
}
