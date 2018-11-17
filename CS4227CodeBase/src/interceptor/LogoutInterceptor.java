package interceptor;


import Account.Account;
import Command.MakeReservationCommand;
import interceptor.Interceptor;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hmaug
 */
public class LogoutInterceptor implements Interceptor{
    Account account;
    
    LogoutInterceptor(Account account) {
        this.account = account;

    }
    
    @Override
    public void logger(Account account){
        String log = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        log = account.getEmail() + "," + (sdf.format(ts)) ;
        
        try(FileWriter filewriter = new FileWriter("src/resources/log.txt",true)){
            filewriter.write("\n User logged in:" + log);
            filewriter.close();
            
        
        }   catch (IOException ex) {
            Logger.getLogger(MakeReservationCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
