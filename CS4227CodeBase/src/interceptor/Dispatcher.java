/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

import Account.Account;
import java.util.*;

/**
 *
 * @author hmaug
 */
public class Dispatcher {
    private ArrayList<Interceptor> iterateList = new ArrayList<>();
    private Interceptor currentInterceptor;
    private static Dispatcher instance = null;
    
    public static Dispatcher getInstance()
    {
        if(instance == null)
            instance = new Dispatcher();
        
        return instance;
    }
    
    public Interceptor dispatch(){
        return currentInterceptor;
    }
    
    public void register(Interceptor interceptor){
        iterateList.add(interceptor);
    }
    
    public void remove(Interceptor interceptor){
        iterateList.remove(interceptor);
    }
    
    public void setCurrentInterceptor(String action, Account account){
        switch(action){
                case "Login":   currentInterceptor = new LoginInterceptor(account); break;
                case "Logout":  currentInterceptor = new LogoutInterceptor(account); break;
                default:        currentInterceptor = null;
    }  
    }
}
