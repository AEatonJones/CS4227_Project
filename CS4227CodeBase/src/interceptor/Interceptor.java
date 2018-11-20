/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

import account.Account;

/**
 *
 * @author hmaug
 */
public interface Interceptor {
    public static final String ACTION = null;
    public abstract void logger(Account account);
}
