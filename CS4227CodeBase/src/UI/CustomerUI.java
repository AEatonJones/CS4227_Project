package UI;

import Account.*;
import Command.CancelReservationCommand;
import Command.Command;
import Command.MakeReservationCommand;
import Data.*;
import Reservation.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
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
        
        String potentialEmail = email.getText();
        String potentialPassword = password.getText();
        
        if(pressed.equals(signIn)) {
            try {
                Account currentAccount = AccountControl.verifyAccount(potentialEmail,potentialPassword);
                if(currentAccount != null) {
                    new AccountMenuUI().initilizeProfile(currentAccount);
                    this.window.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Invalid Information given!");
                }
                
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

            String[] accountDetails = new String[5];
            accountDetails[0] = firstname.getText();
            accountDetails[1] = surname.getText();
            accountDetails[2] = email.getText();
            accountDetails[3] = password.getText();
            accountDetails[4] = number.getText();

            try {
                //send to database
                AccountControl.printToFile(accountDetails);
                
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

class AccountMenuUI implements UI, ActionListener  {
        JFrame frame;
        JButton makeReservation, viewReservation, quit, signOut;
        Account account;
        
        /**
        * Constructor.
        */
        public void initilizeProfile(Account currentAccount) {
            account = currentAccount;
            draw();
        }
        
     /**
     * Sets up GUI.
     */
    @Override
    public void draw() {
        frame = new JFrame("Menu");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(1, 1));

        GridBagConstraints bag = new GridBagConstraints();
        bag.weightx = 1;
        bag.weighty = 1;
        bag.insets = new Insets(5, 0, 5, 0);
        bag.gridwidth = GridBagConstraints.REMAINDER;
        bag.fill = GridBagConstraints.BOTH;

        JPanel buttons = new JPanel();
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttons.setLayout(new GridBagLayout());

        makeReservation = new JButton("MAKE RESERVATION");
        makeReservation.addActionListener(this);
        buttons.add(makeReservation, bag);

        viewReservation = new JButton("VIEW RESERVATION");
        viewReservation.addActionListener(this);
        buttons.add(viewReservation, bag);

        signOut = new JButton("SIGN OUT");
        signOut.addActionListener(this);
        buttons.add(signOut, bag);

        quit = new JButton("QUIT");
        quit.addActionListener(this);
        buttons.add(quit, bag);

        frame.add(buttons);

        frame.setVisible(true);
    }

    /**
     * If a button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource();

        if(pressed.equals(makeReservation)) {
           this.frame.dispose();
           new MakeReservation().initilizeProfile(account);
        }
        else if(pressed.equals(viewReservation)) {
            this.frame.dispose();
            new ViewReservation().initilizeProfile(account);
        }
        else if(pressed.equals(signOut)) {
            this.frame.dispose();
            new CustomerLoginUI().draw();
        }
        else
            System.exit(0);
    }
}

class ViewReservation implements UI,ActionListener {
        JFrame window;
        JList listOfReservations;
        JScrollPane scrollPane;
        DefaultListModel orderListModel;
        JButton cancelReservation, goBack;
        Account account = null;
        ArrayList<String> reservations;
        
        /**
        * Constructor.
        */
        public void initilizeProfile(Account currentAcount) {
            account = currentAcount;
            draw();
        }

        /**
        * Sets up GUI.
        */
        @Override
        public void draw() {       
        window = new JFrame("List of Reservations");
        window.setSize(350, 230);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel title = new JPanel();
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(1, 1));
        
        scrollPane = new JScrollPane();
        orderListModel = new DefaultListModel();
        listOfReservations = new JList(orderListModel);
        scrollPane.setViewportView(listOfReservations);
        reservations = null;

        try {
            //adds box with reservations
            reservations = ReservationControl.obtainReservationInfo(account.getEmail());
        } catch (IOException ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < reservations.size() ;i++ ){
            orderListModel.addElement(reservations.get(i));
        }
        
        title.add(new JLabel("Email,Check-In Date,No. of Nights,Cost,Location"));
        input.add(scrollPane);
        window.add("North", title);
        window.add("Center", input);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2));
        
        cancelReservation = new JButton("Cancel Reservation");
        cancelReservation.addActionListener(this);
        buttons.add(cancelReservation);
        
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
        
    if(pressed.equals(cancelReservation)) {
        try {
            String remove = listOfReservations.getSelectedValue().toString();
            System.out.println(remove);
            String[] resDetails = remove.split(",");
            for(String s:resDetails) {
                System.out.println(s);
            }
            Reservation r = Reservation.makeReservation(resDetails[0], resDetails[1], Integer.parseInt(resDetails[2]), resDetails[3],Float.parseFloat(resDetails[4]));
            Command cmd = new CancelReservationCommand(r,account);
            cmd.execute();
            
            new RemovalConfirmation().initilizeProfile(account,cmd);

            
        } catch (Exception ex) {
            Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.window.dispose();
    }
        

    if(pressed.equals(goBack)) {
            new AccountMenuUI().initilizeProfile(account);
            this.window.dispose();
            }
    }
}
    

class MakeReservation implements UI,ActionListener {
    JFrame window;
    JTextField checkInDate, NoOfNights, NoOfRooms;
    JButton makeReservation, goBack;
    JComboBox reservations;
    Account account;
    
    /**
    * Constructor.
    */
    public void initilizeProfile(Account currentAcount) {
        account = currentAcount;
        draw();
        }
    
    /**
     * Sets up GUI.
     */
    @Override
    public void draw() {
        window = new JFrame("Make Your Reservation");
        window.setSize(350, 230);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        reservations = new JComboBox<>();
        reservations.setModel(new DefaultComboBoxModel<>(new String[] { "London", "Paris" }));
        
        
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(4, 2));
        
        input.add(new JLabel("Check-In Date:"));
        input.add((checkInDate = new JTextField()));
        
        input.add(new JLabel("No. of Nights:"));
        input.add((NoOfNights = new JTextField()));
        
        input.add(new JLabel("No. of Rooms:"));
        input.add((NoOfRooms = new JTextField()));
        
        input.add(new JLabel("Location:"));
        input.add(reservations);

       
        window.add("Center", input);
        
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        
        makeReservation = new JButton("Make Reservation");
        makeReservation.addActionListener(this);
        buttons.add(makeReservation);
        
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
        
        
        if(pressed.equals(makeReservation)) {


            try {
                //send to database
                String cID = checkInDate.getText();
                int nON = Integer.parseInt(NoOfNights.getText());
                int nOR = Integer.parseInt(NoOfRooms.getText());
                String location = reservations.getSelectedItem().toString();
                
                Reservation r = Reservation.makeReservation(account.getEmail(), cID, nON, location);
                Command cmd = new MakeReservationCommand(r,account);
                cmd.execute();
                
                new ReservationConfirmation().initilizeProfile(account,cmd);
                
                
            } catch (Exception ex) {
                Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            
            this.window.dispose();
            
        }
        
        else if(pressed.equals(goBack))
        {
            new AccountMenuUI().initilizeProfile(account);
            this.window.dispose();
        }
    }  
}

class ReservationConfirmation implements UI, ActionListener {
    
    JFrame frame;
    JButton yes,no;
    Account account;
    Command command;
    
    /**
    * Constructor.
    */
    public void initilizeProfile(Account currentAccount, Command cmd) {
        account = currentAccount;
        command = cmd;
        draw();
        }

    /**
     * Sets up GUI.
     */
    @Override
    public void draw() {
        frame = new JFrame("Confirm Reservation");
        JPanel title = new JPanel();
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
        
        yes = new JButton("Yes");
        yes.addActionListener(this);
        buttons.add(yes, constraints);
        
        no = new JButton("No");
        no.addActionListener(this);
        buttons.add(no, constraints);      
        
        frame.add(buttons);
             
        frame.setVisible(true);
    }

    /**
     * If a button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource();
        
        if(pressed.equals(yes)) {
            this.frame.dispose();
            new AccountMenuUI().initilizeProfile(account);
            
        }
        else if(pressed.equals(no)) {
            command.undo();
            this.frame.dispose();
            new AccountMenuUI().initilizeProfile(account);
        }
        else
            System.exit(0);
    }
}

class RemovalConfirmation implements UI, ActionListener {
    
    JFrame frame;
    JButton yes,no;
    Account account;
    Command command;
    
    /**
    * Constructor.
    */
    public void initilizeProfile(Account currentAccount, Command cmd) {
        account = currentAccount;
        command = cmd;
        draw();
        }

    /**
     * Sets up GUI.
     */
    @Override
    public void draw() {
        frame = new JFrame("Removal Confirmation");
        JPanel title = new JPanel();
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
        
        yes = new JButton("Yes");
        yes.addActionListener(this);
        buttons.add(yes, constraints);
        
        no = new JButton("No");
        no.addActionListener(this);
        buttons.add(no, constraints);      
        
        frame.add(buttons);
             
        frame.setVisible(true);
    }

    /**
     * If a button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource();
        
        if(pressed.equals(yes)) {
            this.frame.dispose();
            new AccountMenuUI().initilizeProfile(account);
            
        }
        else if(pressed.equals(no)) {
            command.undo();
            this.frame.dispose();
            new AccountMenuUI().initilizeProfile(account);
        }
        else
            System.exit(0);
    }
}
