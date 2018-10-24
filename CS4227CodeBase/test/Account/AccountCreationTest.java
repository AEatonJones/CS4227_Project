package Account;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaron
 */
public class AccountCreationTest {
    
    public AccountCreationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
    * Test for Account Creation
    */
    @Test
    public void AccountCreationTest() {
        //Setting values for account object
        String fn = "Paul"; String sn = "Smith"; String em = "Paul@ul.ie"; String pw = "Paul1234"; String pn = "0851234567";
        
        //Tests builder for account object
        Account Account1 = new Account.AccountBuilder(fn,sn,em,pw,pn).build();
        
        //Tests getFirstName method
        String firstname = Account1.getFirstName();
        System.out.println(firstname);
        assertEquals(fn, firstname);
        
        //Tests getSurname method
        String surname = Account1.getSurname();
        System.out.println(surname);
        assertEquals(sn, surname);
        
        //Tests getEmail method
        String email = Account1.getEmail();
        System.out.println(email);
        assertEquals(em, email);
        
        //Tests getPassword method
        String password = Account1.getPassword();
        System.out.println(password);
        assertEquals(pw, password);
        
        //Tests getNumber method
        String number = Account1.getNumber();
        System.out.println(number);
        assertEquals(pn, number);
        
        
        
    }
}
