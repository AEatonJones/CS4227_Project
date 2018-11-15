/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Account.Account;
import Account.AccountControl;
import Command.CancelReservationCommand;
import Command.Command;
import Command.MakeReservationCommand;
import Reservation.Reservation;
import Reservation.ReservationMemento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.CustomerUI.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hmaug
 */
public class CustomerUIAdapter implements UI , ActionListener{
    private CustomerUI ui = new CustomerUI();
    private CustomerSignIn signIn;
    private CustomerRegister register;
    private CustomerLoginUI login;
    private MakeReservation make;
    private ViewReservation view;
    private AccountMenuUI menu;
    private ViewPossibleRooms rooms;
    
    private Account account = null;
    private Reservation reservation = null;
    private ReservationMemento memento = null;
    
    public CustomerUIAdapter()
    {
        drawLogIn();
    }
    
    @Override
    public void drawSignIn() {
        signIn = ui.new CustomerSignIn(this);
        signIn.draw();
    }

    @Override
    public void drawRegister() {
        register = ui.new CustomerRegister(this);
        register.draw();
    }

    @Override
    public void drawLogIn() {
        login = ui.new CustomerLoginUI(this);
        login.draw();
    }

    @Override
    public void drawMainMenu() {
        menu = ui.new AccountMenuUI(account, this);
        menu.draw();
    }

    @Override
    public void drawViewReservations() {
        view = ui.new ViewReservation(account, this);
        view.draw();
    }

    @Override
    public void drawMakeReservations() {
        make = ui.new MakeReservation(this);
        make.initilizeProfile(account);
    }

    @Override
    public void drawAvailableRooms() {
        rooms = ui.new ViewPossibleRooms(this);
        rooms.initilizeProfile(account, reservation, memento);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand){
            //CustomerLogInUI
            case "Log In":                      logIn();            break;
            case "Log in Register":             logInReg();         break;
            case "Quit":                        quit();             break;
            
            //CustomerSignIn
            case "Sign In":                     signIn();           break;
            case "Sign In Go Back":             goBack("Sign In");  break;
            
            //CustomerRegister
            case "Register":                    registerAccount();  break;
            case "Register Go Back":            goBack("Register"); break;
            
            //AccountMenuUI
            case "Menu Make":                   menuMakeRes();      break;
            case "Menu View":                   menuViewRes();      break;
            case "Sign Out":                    signOut();          break;
            
            //ViewReservation
            case "Cancel Reservation":          cancel();           break;
            case "View Go Back":                goBack("View");     break;
            
            //MakeReservation
            case "Search":                      search();           break;
            case "Make Reservation Go Back":    goBack("Make Res"); break;
            
            //ViewPossibleRooms
            case "Select Room":                 selectRoom();       break;
            case "Rooms Go Back":               goBack("Rooms");    break;
        }
    }
    
    private void logIn()
    {
        login.frame.dispose();
        drawSignIn();
    }
    
    private void logInReg()
    {
        login.frame.dispose();
        drawRegister();
    }
    
    private void quit()
    {
        System.exit(0);
    }
    
    private void signIn()
    {
        try 
        {
            account = AccountControl.verifyAccount(signIn.email.getText(),signIn.password.getText());
        } 
        
        catch (IOException ex) 
        {
            Logger.getLogger(CustomerSignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(account != null) 
        {
            drawMainMenu();
            signIn.window.dispose();
        }
            else 
            {
                JOptionPane.showMessageDialog(null,"Invalid Information given!");
            }
    }
    
    private void goBack(String uiName)
    {
        switch(uiName)
        {
            case "Sign In":     signIn.window.dispose();    drawLogIn();            break;
            
            case "Register":    register.window.dispose();  drawLogIn();            break;
            
            case "View":        view.window.dispose();      drawMainMenu();         break;
            
            case "Make Res":    make.window.dispose();      drawMainMenu();         break;
            
            case "Rooms":       rooms.window.dispose();     memento.restoreState(memento,account, this); break;
        }
    }
    
    private void registerAccount()
    {
        String[] accountDetails = new String[5];
        accountDetails[0] = register.firstname.getText();
        accountDetails[1] = register.surname.getText();
        accountDetails[2] = register.email.getText();
        accountDetails[3] = register.password.getText();
        accountDetails[4] = register.number.getText();
        
        try 
        {
            AccountControl.printToFile(accountDetails);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        drawLogIn();
        register.window.dispose();
    }
    
    private void menuMakeRes()
    {
        menu.frame.dispose();
        drawMakeReservations();
    }
    
    private void menuViewRes()
    {
        menu.frame.dispose();
        drawViewReservations();
    }
    
    private void signOut()
    {
        menu.frame.dispose();
        drawSignIn();
    }
    
    private void search()
    {
        try {
            String cID = make.checkInDate.getText();
            int nON = Integer.parseInt(make.NoOfNights.getText());
            String location = make.reservations.getSelectedItem().toString();

            reservation = Reservation.makeReservation(account.getEmail(), cID, nON, location,"");
            memento = new ReservationMemento(reservation,account);
            
            rooms = ui.new ViewPossibleRooms(this);
            rooms.initilizeProfile(account,reservation,memento);
            make.window.dispose();
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cancel()
    {
        try {
            String remove = view.listOfReservations.getSelectedValue().toString();
            System.out.println(remove);
            String[] resDetails = remove.split(",");
            for(String s:resDetails) {
                System.out.println(s);
            }
            Reservation r = Reservation.makeReservation(resDetails[0], resDetails[1], Integer.parseInt(resDetails[2]), resDetails[3],resDetails[4],Float.parseFloat(resDetails[5]));
            Command cmd = new CancelReservationCommand(r,account);
            cmd.execute();

            boolean removedConfirm = false;
            while (!removedConfirm) {
                int option = JOptionPane.showConfirmDialog(null, "You have just canceled your booking.\nHit Cancel if you wish to undo otherwise hit Ok.", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if(option == JOptionPane.OK_OPTION) {

                    removedConfirm = true;
                }
                else if(option == JOptionPane.CANCEL_OPTION) {
                    cmd.undo();

                    removedConfirm = true;
                }
            }

            menu = ui.new AccountMenuUI(account, this);
            menu.initilizeProfile(account);

        } catch (Exception ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.window.dispose();
    }
    
    private void selectRoom()
    {
        try {
                String room = rooms.listOfRooms.getSelectedValue().toString();
                String[] roomDetails = room.split(",");
                reservation.setRoomID(roomDetails[0]);

                //set the cost which is given in the room and passed into the payment controller
                Command cmd = new MakeReservationCommand(reservation,account);
                cmd.execute();
                boolean bookedConfirm = false;
                boolean anotherBooking = false;

                while (!bookedConfirm) {
                    int option = JOptionPane.showConfirmDialog(null, "You have just reservered your booking\nHit Cancel if you wish to undo otherwise hit Ok.", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                    if(option == JOptionPane.OK_OPTION) {
                        bookedConfirm = true;
                    }
                    else if(option == JOptionPane.CANCEL_OPTION) {
                        cmd.undo();
                        bookedConfirm = true;
                    }
                }

                while (!anotherBooking) {
                    int option = JOptionPane.showConfirmDialog(null, "Would you like to reserve another booking?.", "Another Booking?", JOptionPane.OK_CANCEL_OPTION);
                    if(option == JOptionPane.OK_OPTION) {
                        memento.restoreState(memento,account);
                        anotherBooking = true;
                    }
                    else if(option == JOptionPane.CANCEL_OPTION) {
                        menu = ui.new AccountMenuUI(account, this);
                        anotherBooking = true;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
            }
            rooms.window.dispose();
    }
}
