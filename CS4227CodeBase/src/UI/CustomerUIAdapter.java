/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.CustomerUI.*;

/**
 *
 * @author hmaug
 */
public class CustomerUIAdapter implements UI , ActionListener{
    private CustomerUI ui = new CustomerUI();
    @Override
    public void drawSignIn() {
        CustomerSignIn customerSignIn;
        customerSignIn = ui.new CustomerSignIn(this);
        customerSignIn.draw();
    }

    @Override
    public void drawRegister() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawLogIn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawMainMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawViewReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawMakeReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawAvailableRooms() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        switch(actionCommand){
            case "Log in":  /*GOTO CustomerSignIn*/                 break;
            case "Log in Register":/*GOTO CustomerRegister*/        break;
            case "Quit":/*EXIT system*/                             break;
            case "Sign In":/*GOTO AccountMenuUI*/                   break;
            case "Sign In Go Back":/*GOTO CustomerLogin*/           break;
            case "Register":/*GOTO CustomerLogin*/                  break;
            case "Register Go Back":/*GOTO CustomerLogin*/          break;
            case "Menu Make":/*GOTO MakeReservation*/               break;
            case "Menu View":/*GOTO ViewReservation*/               break;
            case "Sign Out":/*GOTO CustomerSignIn*/                 break;
            case "Cancel Reservation":/*Cancel & Confirm*/          break;
            case "View Go Back":/*GOTO AccountMenuUI*/              break;
            case "Search":/*GOTO ViewPossibleRooms*/                break;
            case "Make Reservation Go Back":/*GOTO AccountMenuUI*/  break;
            case "Select Room":/**/                                 break;
            case "Rooms Go Back":/*GOTO MakeReservation*/           break;
        }
    }
}
