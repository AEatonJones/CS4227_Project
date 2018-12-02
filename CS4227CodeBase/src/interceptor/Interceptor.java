package interceptor;

import account.Account;

public interface Interceptor {
    public abstract void logger(Account account);
    public abstract String getAction();
}
