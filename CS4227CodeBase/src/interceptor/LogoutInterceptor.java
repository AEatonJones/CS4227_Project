package interceptor;

import account.Account;
import command.MakeReservationCommand;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogoutInterceptor implements Interceptor{
    Account account;
    private static final String ACTION = "Logout";
    
    public LogoutInterceptor(Account account) {
        this.account = account;

    }
    
    @Override
    public void logger(Account account){
        String log = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        log = account.getEmail() + "," + (sdf.format(ts)) ;
        
        try(FileWriter filewriter = new FileWriter("src/resources/log.txt",true)){
            filewriter.write("User logged out:" + log + "\n");
            
        
        }   catch (IOException ex) {
            Logger.getLogger(MakeReservationCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getAction() {  
        return ACTION;
    }
}
