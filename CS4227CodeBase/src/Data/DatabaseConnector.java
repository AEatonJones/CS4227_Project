package Data;

import Account.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DatabaseConnector {
    
    private static DatabaseConnector instance = null;
    
    public static DatabaseConnector getInstance()
    {
        if(instance == null)
            instance = new DatabaseConnector();
        
        return instance;
    }
    
    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:derby://localhost:1527/Hotel_DB";
            String username = "root";
            String password = "";
            Class.forName(driver);
            
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Hello it works");
            return conn;
        }catch(Exception e) {
            System.out.print(e);
        }
        return null;
    }
    
        public static Account getAccountInfo(String[] accInfo) throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("select * from account where email = '" + accInfo[0] + "' AND password = '" + accInfo[1] + "'");
            ResultSet resultSet = statement.executeQuery();
           
            Account account = new Account.AccountBuilder("","","","","").build();
            while(resultSet.next()){
                account.setFirstName(resultSet.getString("fname"));
                account.setSurname(resultSet.getString("sname"));
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));
                account.setNumber(resultSet.getString("phone_number"));
               
            }
            return account;
           
        }catch(Exception e){System.out.println(e);}
        return null;
       
    }
    public static void postAccountInfo(String[] accInfo) throws Exception{
        
        
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("INSERT INTO account (First Name, Surname, Email, Password, Number)" + " VALUES ('" + accInfo[0] + "','" + accInfo[1] + "',"
                    + "'" + accInfo[2] + "', '" + accInfo[3] + "', '" + accInfo[4] + "')");
           
            posted.executeUpdate();
        } catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Insert Completed.");
        }
    }
    
}

