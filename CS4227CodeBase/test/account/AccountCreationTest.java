package account;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountCreationTest {
    
    @Test
    public void creationOfAnAccountObject() {
        //Setting values for account object
        String fn = "Paul"; 
        String sn = "Smith"; 
        String em = "Paul@ul.ie"; 
        String pw = "Paul1234"; 
        String pn = "0851234567";
        
        //Tests builder for account object
        Account account1 = new Account.AccountBuilder(fn,sn,em,pw,pn).build();
        
        //Tests getFirstName method
        String firstname = account1.getFirstName();
        System.out.println(firstname);
        assertEquals(fn, firstname);
        
        //Tests getSurname method
        String surname = account1.getSurname();
        System.out.println(surname);
        assertEquals(sn, surname);
        
        //Tests getEmail method
        String email = account1.getEmail();
        System.out.println(email);
        assertEquals(em, email);
        
        //Tests getPassword method
        String password = account1.getPassword();
        System.out.println(password);
        assertEquals(pw, password);
        
        //Tests getNumber method
        String number = account1.getNumber();
        System.out.println(number);
        assertEquals(pn, number);
        
        
        
    }
}
