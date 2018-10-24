package UI;

import Account.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CustomerUI implements UI {

    /**
     * Constructor.
     */
    public CustomerUI() {
     
    }
    
    public void draw() {
        new CustomerLoginUI().draw();
    }
}

class CustomerLoginUI implements UI, ActionListener {
    
    JFrame frame;
    JButton signIn,register,quit;

    /**
     * Sets up GUI.
     */
    @Override
    public void draw() {
        frame = new JFrame("Login");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(1, 1));
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        

        JPanel buttons = new JPanel();
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttons.setLayout(new GridBagLayout());
        
        signIn = new JButton("SIGN IN");
        signIn.addActionListener(this);
        buttons.add(signIn, constraints);
        
        register = new JButton("REGISTER");
        register.addActionListener(this);
        buttons.add(register, constraints);
        
        quit = new JButton("QUIT");
        quit.addActionListener(this);
        buttons.add(quit, constraints);
        
        frame.add(buttons);
             
        frame.setVisible(true);
    }

    /**
     * If a button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource();
        
        if(pressed.equals(signIn)) {
            this.frame.dispose();
            //new CustomerSignIn().draw();
            
        }
        else if(pressed.equals(register)) {
            this.frame.dispose();
            //new CustomerRegister().draw();
        }
        else
            System.exit(0);
    }
}
