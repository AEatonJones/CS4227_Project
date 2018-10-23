package Account;

public class Account {

    private String firstname, surname, email, password, number;
    
    /**
     * Constructor for Account Object
     * @param firstname
     * @param surname
     * @param email
     * @param password
     * @param number
     */
    public Account(String firstname, String surname, String email, String password, String number) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.number = number;
    }
    
        /**
        * Getter which gets the customers first name.
        * @return First name of customer.
        */
        public String getFirstName() {
        return this.firstname;
        }
        
        /**
        * Getter which gets the customers first name.
        * @return  Surname of customer.
        */
        public String getSurname() {
            return this.surname;
        }
    
        /**
        * Getter which gets the customers first name.
        * @return Email of customer.
        */   
        public String getEmail() {
        return this.email;
        }
        
        /**
        * Getter which gets the customers first name.
        * @return Password of customer.
        */
        public String getPassword() {
        return this.password;
        }
        
        /**
        * Getter which gets the customers first name.
        * @return Phone number of customer.
        */
        public String getNumber() {
        return this.number;
        }
}
