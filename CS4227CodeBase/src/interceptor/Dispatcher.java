/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

import account.Account;
import java.util.*;

public class Dispatcher {
    private ArrayList<Interceptor> iterateList = new ArrayList<>(0);
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
        if(!iterateList.contains(interceptor))
            iterateList.add(interceptor);
    }
    
    public void remove(Interceptor interceptor){
        iterateList.remove(interceptor);
    }
    
    public void setCurrentInterceptor(String action, Account account){
        Interceptor target;
        
        switch(action){
                case "Login":   target = new LoginInterceptor(account); break;
                case "Logout":  target = new LogoutInterceptor(account); break;
                default:        target = null;
        }
        for(Interceptor i: iterateList) {
           if(i.getAction().equals(action)) {
               currentInterceptor = i;
               break;
           } 
        }
    }
}
