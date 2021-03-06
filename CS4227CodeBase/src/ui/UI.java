/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
* Is an interface that all UI's must follow.
*/
public interface UI {
    public abstract void drawSignIn();
    public abstract void drawRegister();
    public abstract void drawLogIn();
    public abstract void drawModMenu();
    public abstract void drawMainMenu();
    public abstract void drawExtras();
    public abstract void drawViewReservations();
    public abstract void drawMakeReservations();
    public abstract void drawAvailableRooms();
}
