package UI;

import Account.*;
import Data.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            new CustomerSignIn().draw();
            
        }
        else if(pressed.equals(register)) {
            this.frame.dispose();
            new CustomerRegister().draw();
        }
        else
            System.exit(0);
    }
}

class CustomerSignIn implements UI,ActionListener {
    JFrame window;
    JTextField email;
    JPasswordField password;
    JButton signIn, goBack;
    
    /**
     * Sets up GUI.
     */
    @Override
    public void draw() {
        window = new JFrame("Sign In");
        window.setSize(325, 140);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(2, 2));
        
        input.add(new JLabel("Email:"));
        input.add((email = new JTextField()));
        
        input.add(new JLabel("Password:"));
        input.add((password = new JPasswordField()));
        
        
        window.add("Center", input);
        
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        
        signIn = new JButton("Sign In");
        signIn.addActionListener(this);
        buttons.add(signIn);
        
        goBack = new JButton("Go Back");
        goBack.addActionListener(this);
        buttons.add(goBack);
        
        window.add("South", buttons);
        window.setVisible(true);
    }

    /**
     * If a button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton)e.getSource();
        
        String[] accInfo = new String[2];
        accInfo[0] = email.getText();
        accInfo[1] = password.getText();
        
        if(pressed.equals(signIn)) {
            try {
                DatabaseConnector.getConnection();
                Account currentAccount = DatabaseConnector.getAccountInfo(accInfo);
                System.out.println(currentAccount.getFirstName());
                System.out.println(currentAccount.getSurname());
            } catch (Exception ex) {
                Logger.getLogger(CustomerSignIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(pressed.equals(goBack)){
            new CustomerLoginUI().draw();
            this.window.dispose();
        }
    }
    
}

class CustomerRegister implements UI,ActionListener {
    JFrame window;
    JTextField firstname, surname, password, email, number;
    JButton register, goBack;
    
    /**
     * Sets up GUI.
     */
    @Override
    public void draw() {
        window = new JFrame("Register your Account");
        window.setSize(350, 230);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(5, 2));
        
        input.add(new JLabel("Enter your firstname:"));
        input.add((firstname = new JTextField()));
        
        input.add(new JLabel("Enter your surname:"));
        input.add((surname = new JTextField()));
        
        input.add(new JLabel("Enter your password:"));
        input.add((password = new JTextField()));
        
        input.add(new JLabel("Enter your email address:"));
        input.add((email = new JTextField()));
        
        input.add(new JLabel("Enter your number:"));
        input.add((number = new JTextField()));
        
        window.add("Center", input);
        
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        
        register = new JButton("Register");
        register.addActionListener(this);
        buttons.add(register);
        
        goBack = new JButton("Go Back");
        goBack.addActionListener(this);
        buttons.add(goBack);
        
        window.add("South", buttons);
        window.setVisible(true);
    }

    /**
     * If a button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton)e.getSource();
        
        
        if(pressed.equals(register)) {

            String[] customerDetails = new String[5];
            customerDetails[0] = firstname.getText();
            customerDetails[1] = surname.getText();
            customerDetails[2] = email.getText();
            customerDetails[3] = password.getText();
            customerDetails[4] = number.getText();

            try {
                //send to database
                DatabaseConnector.postAccountInfo(customerDetails);
                System.out.println("Account Info Uploaded");
            } catch (Exception ex) {
                Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            new CustomerLoginUI().draw();
            this.window.dispose();
            
        }
        
        else if(pressed.equals(goBack))
        {
            new CustomerLoginUI().draw();
            this.window.dispose();
        }
    }  
}
