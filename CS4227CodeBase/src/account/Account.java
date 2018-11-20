package account;

public class Account {

    private String firstname;
    private String surname;
    private String email;
    private String password;
    private String number;
    
    private Account(AccountBuilder builder) {
        this.firstname = builder.firstname;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.number = builder.number;
    }
    
        public String getFirstName() {
            return this.firstname;
        }

        public void setFirstName(String fn) {
            this.firstname = fn;
        }
        

        public String getSurname() {
            return this.surname;
        }

        public void setSurname(String sn) {
            this.surname = sn;
        }
     
        public String getEmail() {
            return this.email;
        }

        public void setEmail(String em) {
            this.email = em;
        }
        
        public String getPassword() {
            return this.password;
        }

        public void setPassword(String pw) {
            this.password = pw;
        }
        
        public String getNumber() {
            return this.number;
        }
        
        public void setNumber(String pn) {
            this.number = pn;
        }
        
       	//Builder Class
	public static class AccountBuilder{

            private String firstname;
            private String surname;
            private String email;
            private String password;
            private String number;

            public AccountBuilder(String fn, String sn, String em, String pw, String pn){
                    this.firstname=fn;
                    this.surname= sn;
                    this.email= em;
                    this.password= pw;
                    this.number= pn;
            }

            public Account build(){
                    return new Account(this);
            }
	}
}

