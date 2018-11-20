package ui;

import command.CancelReservationCommand;
import command.MakeReservationCommand;
import account.Account;
import account.AccountControl;
import reservation.Reservation;
import interceptor.Dispatcher;
import interceptor.Interceptor;
import interceptor.LoginInterceptor;
import interceptor.LogoutInterceptor;
import java.io.BufferedReader;
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
        
        System.out.println("PLEASE ENTER EMAIL, (2 LOGOUT");
        String potentialEmail = in.nextLine();
        email = potentialEmail;
        if(potentialEmail.equals("2")){
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
       System.out.println("WELCOME! 1)SIGN IN 2)REGISTER 3)MOD MENU 4)QUIT");
       int input = Integer.parseInt(in.nextLine());
       switch(input){
           case 1: drawSignIn(); break;
           case 2: drawRegister(); break;
           case 3: drawModMenu(); break;
           case 4: System.out.println("GOODBYE"); System.exit(0); break;
           }
    }
       
    public void drawModMenu(){
    System.out.println("PLEASE ENTER INTERCEPTOR. 1)LOGIN 2)LOGOUT 3) BACK");
    int input = Integer.parseInt(in.nextLine());
    if(input == 1){
        LoginInterceptor interceptor =  new LoginInterceptor(currentAccount);
        Dispatcher.getInstance().register(interceptor);
        drawModMenu();
    }
    else if (input == 2){
        LogoutInterceptor interceptor = new LogoutInterceptor(currentAccount);
        Dispatcher.getInstance().register(interceptor);
        drawModMenu();
    }
    else
        drawLogIn();

 }
       
    

    @Override
    public void drawMainMenu() {
        Dispatcher dispatcher = Dispatcher.getInstance();
        Interceptor interceptor;
       System.out.println("1)MAKE RESERVATION 2)VIEW RESERVATION 3)SIGN OUT 4)QUIT");
       int input = Integer.parseInt(in.nextLine());
       switch(input){
           case 1: drawMakeReservations(); break;
           case 2: drawViewReservations(); break;
           case 3: dispatcher.setCurrentInterceptor("Logout", currentAccount);
                   interceptor = dispatcher.dispatch();
                   interceptor.logger(currentAccount);
                   drawLogIn();
                   break;
           case 4: System.out.println("GOODBYE");
                   dispatcher.setCurrentInterceptor("Logout", currentAccount);
                   interceptor = dispatcher.dispatch();
                   interceptor.logger(currentAccount);
                   System.exit(0); 
                   break;
           }
    }

    @Override
    public void drawViewReservations() {
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/resources/reservations.txt"))){
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
        if(input.equals("2")){
           drawMakeReservations();
           }
        else {
            try {
                String line;
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/resources/rooms.txt"));) {
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
                }
            }
            catch (IOException ex) {
                Logger.getLogger(CommandLineInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            } 
    }
      

}
