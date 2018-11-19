package UI;

import Account.*;
import Command.CancelReservationCommand;
import Command.Command;
import Command.MakeReservationCommand;
import Reservation.*;
import hotel.room.RoomControl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CustomerUI   {
    private ReservationMemento m;
    private Account a;
    /**
     * Constructor.
     */
    public CustomerUI() {
     
    }
    
    public void draw() {
        new CustomerLoginUI().draw();
    }
    
    public void restoreBooking(ReservationMemento m,Account a) {
        new MakeReservation().restoringBooking(a,m);
    }

    public void restoreBooking(ReservationMemento m,Account a, ActionListener listener) {
        new MakeReservation(listener).restoringBooking(a,m);
    }

    class CustomerLoginUI  implements ActionListener {

        JFrame frame;
        JButton signIn,register,quit;
        private ActionListener listener;

        public CustomerLoginUI()
        {
            this.listener = this;
        }

        public CustomerLoginUI(ActionListener listener)
        {
            this.listener = listener;
        }

        /**
         * Sets up GUI.
         */

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
            signIn.addActionListener(listener);
            signIn.setActionCommand("Log In");
            buttons.add(signIn, constraints);

            register = new JButton("REGISTER");
            register.addActionListener(listener);
            register.setActionCommand("Register");
            buttons.add(register, constraints);

            quit = new JButton("QUIT");
            quit.addActionListener(listener);
            quit.setActionCommand("Quit");
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

    class CustomerSignIn   implements ActionListener {
        JFrame window;
        JTextField email;
        JPasswordField password;
        JButton signIn, goBack;
        private ActionListener listener;
        /**
         * Sets up GUI.
         */

        public CustomerSignIn()
        {
            this.listener = this;
        }

        public CustomerSignIn(ActionListener listener)
        {
            this.listener = listener;
        }

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
            signIn.addActionListener(listener);
            signIn.setActionCommand("Sign In");
            buttons.add(signIn);

            goBack = new JButton("Go Back");
            goBack.addActionListener(listener);
            goBack.setActionCommand("Sign In Go Back");
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
                        new AccountMenuUI(currentAccount);
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

    class CustomerRegister   implements ActionListener {
        JFrame window;
        JTextField firstname, surname, password, email, number;
        JButton register, goBack;
        private ActionListener listener;

        public CustomerRegister()
        {
            this.listener = this;
        }

        public CustomerRegister(ActionListener listener)
        {
            this.listener = listener;
        }

        /**
         * Sets up GUI.
         */

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
            register.addActionListener(listener);
            register.setActionCommand("Register");
            buttons.add(register);

            goBack = new JButton("Go Back");
            goBack.addActionListener(listener);
            goBack.setActionCommand("Register Go Back");
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

                } catch (IOException ex) {
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

    class AccountMenuUI  implements ActionListener  {
        JFrame frame;
        JButton checkIn, checkOut, makeReservation, viewReservation, quit, signOut;
        Account account;
        private ActionListener listener;

        public AccountMenuUI(Account currentAccount)
        {
            this.listener = this;
            initilizeProfile(currentAccount);
        }

        public void initilizeProfile(Account currentAccount) {
            account = currentAccount;
            draw();
        }

        public AccountMenuUI(Account currentAccount, ActionListener listener)
        {
            this.listener = listener;
            account = currentAccount;
        }

        public void draw() {
            frame = new JFrame("Menu");
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
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
            makeReservation.addActionListener(listener);
            makeReservation.setActionCommand("Menu Make");
            buttons.add(makeReservation, bag);

            viewReservation = new JButton("VIEW RESERVATION");
            viewReservation.addActionListener(listener);
            viewReservation.setActionCommand("Menu View");
            buttons.add(viewReservation, bag);

            signOut = new JButton("SIGN OUT");
            signOut.addActionListener(listener);
            signOut.setActionCommand("Sign Out");
            buttons.add(signOut, bag);

            quit = new JButton("QUIT");
            quit.addActionListener(listener);
            quit.setActionCommand("quit");
            buttons.add(quit, bag);

            frame.add(buttons);

            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton pressed = (JButton) e.getSource();

            if(pressed.equals(checkIn)) {
                //ability to check in your reservations
            }
            else if(pressed.equals(checkOut)) {
                //ability to check out your reservations
            }
            else if(pressed.equals(makeReservation)) {
               this.frame.dispose();
               new MakeReservation().initilizeProfile(account);
            }
            else if(pressed.equals(viewReservation)) {
                this.frame.dispose();
                new ViewReservation(account);
            }
            else if(pressed.equals(signOut)) {
                this.frame.dispose();
                new CustomerLoginUI().draw();
            }
            else if(pressed.equals(quit)) {
                this.frame.dispose();
            }
        }
    }

    class ViewReservation   implements ActionListener {
            JFrame window;
            JList listOfReservations;
            JScrollPane scrollPane;
            DefaultListModel orderListModel;
            JButton cancelReservation, goBack;
            Account account = null;
            ArrayList<String> reservations;
            private ActionListener listener;

            public ViewReservation(Account currentAccount)
            {
                this.listener = this;
                initilizeProfile(currentAccount);
            }

            public void initilizeProfile(Account currentAcount) {
                account = currentAcount;
                draw();
            }

            public ViewReservation(Account currentAccount, ActionListener listener)
            {
                this.listener = listener;
                account = currentAccount;
            }

            public void draw() {       
            window = new JFrame("List of Reservations");
            window.setSize(350, 230);
            window.setResizable(false);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

                reservations = ReservationControl.obtainReservationInfo(account.getEmail());
            } catch (IOException ex) {
                Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
            }

            for(int i = 0; i < reservations.size() ;i++ ){
                orderListModel.addElement(reservations.get(i));
            }

            title.add(new JLabel("Email,Check-In Date,No. of Nights,Room-ID,Location,Cost"));
            input.add(scrollPane);
            window.add("North", title);
            window.add("Center", input);

            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(1, 2));

            cancelReservation = new JButton("Cancel Reservation");
            cancelReservation.addActionListener(listener);
            cancelReservation.setActionCommand("Cancel");
            buttons.add(cancelReservation);

            goBack = new JButton("Go Back");
            goBack.addActionListener(listener);
            goBack.setActionCommand("View Go Back");
            buttons.add(goBack);

            window.add("South", buttons);
            window.setVisible(true);

        }
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

                new AccountMenuUI(account);

            } catch (Exception ex) {
                Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.window.dispose();
        }


        if(pressed.equals(goBack)) {
                new AccountMenuUI(account);
                this.window.dispose();
                }
        }
    }


    class MakeReservation   implements ActionListener {
        JFrame window;
        JTextField checkInDate, NoOfNights;
        JButton makeReservation, goBack;
        JComboBox reservations;
        Account account;
        private ActionListener listener;

        public MakeReservation()
        {
            this.listener = this;
        }

        public MakeReservation(ActionListener listener)
        {
            this.listener = listener;
        }

        public void initilizeProfile(Account currentAcount) {
            account = currentAcount;
            draw();
        }

        void restoringBooking(Account a, ReservationMemento m) {
            account = a;
            draw();

            NoOfNights.setText(Integer.toString(m.reservation.getNoNights()));
            checkInDate.setText(m.reservation.getStartDate());
        }

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
            input.setLayout(new GridLayout(3, 2));

            input.add(new JLabel("Check-In Date:"));
            input.add((checkInDate = new JTextField()));

            input.add(new JLabel("No. of Nights:"));
            input.add((NoOfNights = new JTextField()));

            input.add(new JLabel("Location:"));
            input.add(reservations);

            window.add("Center", input);

            JPanel buttons = new JPanel();
            buttons.setLayout(new FlowLayout());

            makeReservation = new JButton("Search");
            makeReservation.addActionListener(listener);
            makeReservation.setActionCommand("Search");
            buttons.add(makeReservation);

            goBack = new JButton("Go Back");
            goBack.addActionListener(listener);
            goBack.setActionCommand("Make Reservation Go Back");
            buttons.add(goBack);

            window.add("South", buttons);
            window.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton pressed = (JButton)e.getSource();

            if(pressed.equals(makeReservation)) {
                try {
                    String cID = checkInDate.getText();
                    int nON = Integer.parseInt(NoOfNights.getText());
                    String location = reservations.getSelectedItem().toString();

                    Reservation r = Reservation.makeReservation(account.getEmail(), cID, nON, location,"");
                    ReservationMemento m = new ReservationMemento(r,account);
                    new ViewPossibleRooms().initilizeProfile(account,r,m);
                } catch (Exception ex) {
                    Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            this.window.dispose();  
            }     

            else if(pressed.equals(goBack))
            {
                new AccountMenuUI(account);
                this.window.dispose();
            }
        }  
    }

    class ViewPossibleRooms   implements ActionListener {
            JFrame window;
            JList listOfRooms;
            JScrollPane scrollPane;
            DefaultListModel orderListModel;
            JButton MakeReservation, goBack;
            Account account = null;
            ArrayList<String> rooms;
            Reservation reservation;
            ReservationMemento mementoState;
            private ActionListener listener;


            public void initilizeProfile(Account currentAcount, Reservation res, ReservationMemento m) {
                account = currentAcount;
                reservation = res;
                mementoState = m;
                draw();
            }

            public ViewPossibleRooms()
            {
                this.listener = this;
            }

            public ViewPossibleRooms(ActionListener listener)
            {
                this.listener = listener;
            }

            public void draw() {       
                window = new JFrame("List of Rooms");
                window.setSize(350, 230);
                window.setResizable(false);
                window.setLocationRelativeTo(null);
                window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
                window.setLayout(new BorderLayout());

                JPanel title = new JPanel();
                JPanel input = new JPanel();
                input.setLayout(new GridLayout(2, 1));

                scrollPane = new JScrollPane();
                orderListModel = new DefaultListModel();
                listOfRooms = new JList(orderListModel);
                scrollPane.setViewportView(listOfRooms);
                rooms = null;

                try {
                    //adds box with rooms
                    rooms = RoomControl.obtainRoomInfo(reservation.getLocation());
                } catch (IOException ex) {
                    Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
                }

                for(int i = 0; i < rooms.size() ;i++ ){
                    orderListModel.addElement(rooms.get(i));
                }

                title.add(new JLabel("Select Available Room"));
                input.add(new JLabel("(Room-ID,Location,Single Beds,Double Beds,Cost)"));
                input.add(scrollPane);
                window.add("North", title);
                window.add("Center", input);

                JPanel buttons = new JPanel();
                buttons.setLayout(new GridLayout(1, 2));

                MakeReservation = new JButton("Select Room");
                MakeReservation.addActionListener(listener);
                MakeReservation.setActionCommand("Select Room");
                buttons.add(MakeReservation);

                goBack = new JButton("Go Back");
                goBack.addActionListener(listener);
                goBack.setActionCommand("Rooms Go Back");
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

        if(pressed.equals(MakeReservation)) {
            try {
                String room = listOfRooms.getSelectedValue().toString();
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
                        //Use visitor to visit room:
                            //Using room data, construct room object
                            //Make visitor singleton to keep data consistent
                            //Use txt file to preserve visitor data
                    }
                    else if(option == JOptionPane.CANCEL_OPTION) {
                        cmd.undo();
                        bookedConfirm = true;
                    }
                }

                while (!anotherBooking) {
                    int option = JOptionPane.showConfirmDialog(null, "Would you like to reserve another booking?.", "Another Booking?", JOptionPane.OK_CANCEL_OPTION);
                    if(option == JOptionPane.OK_OPTION) {
                        mementoState.restoreState(mementoState,account);
                        anotherBooking = true;
                    }
                    else if(option == JOptionPane.CANCEL_OPTION) {
                        new AccountMenuUI(account);
                        anotherBooking = true;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ViewReservation.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.window.dispose();
        }

        else if(pressed.equals(goBack)) { 
            mementoState.restoreState(mementoState,account);
            this.window.dispose();
        }


        }
    }
}