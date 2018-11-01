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
    private Account(AccountBuilder builder) {
        this.firstname = builder.firstname;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.number = builder.number;
    }
    
        /**
        * Getter which gets the customers first name.
        * @return First name of customer.
        */
        public String getFirstName() {
            return this.firstname;
        }
        /**
        * Setter which sets the customers first name.
        */
        public void setFirstName(String fn) {
            this.firstname = fn;
        }
        
        /**
        * Getter which gets the customers first name.
        * @return  Surname of customer.
        */
        public String getSurname() {
            return this.surname;
        }
        /**
        * Setter which sets the customers last name.
        */
        public void setSurname(String sn) {
            this.surname = sn;
        }
    
        /**
        * Getter which gets the customers first name.
        * @return Email of customer.
        */   
        public String getEmail() {
            return this.email;
        }
        /**
        * Setter which sets the customers email.
        */
        public void setEmail(String em) {
            this.email = em;
        }
        
        /**
        * Getter which gets the customers first name.
        * @return Password of customer.
        */
        public String getPassword() {
            return this.password;
        }
        /**
        * Setter which sets the customers Password.
        */
        public void setPassword(String pw) {
            this.password = pw;
        }
        
        /**
        * Getter which gets the customers first name.
        * @return Phone number of customer.
        */
        public String getNumber() {
            return this.number;
        }
        /**
        * Setter which sets the customers email.
        */
        public void setNumber(String pn) {
            this.number = pn;
        }
        
       	//Builder Class
	public static class AccountBuilder{

                private String firstname, surname, email, password, number;
		
                /**
                * Constructor for Account Object
                * @param fn - first name
                * @param sn - surname
                * @param em - email
                * @param pw - password
                * @param pn - phone number
                */
		public AccountBuilder(String fn, String sn, String em, String pw, String pn){
			this.firstname=fn;
			this.surname= sn;
                        this.email= em;
                        this.password= pw;
                        this.number= pn;
		}

                /**
                * Account object builder
                * @return Account Object.
                */
		public Account build(){
			return new Account(this);
		}
	}
}

