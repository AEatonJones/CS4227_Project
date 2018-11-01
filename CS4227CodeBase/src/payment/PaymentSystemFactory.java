package payment;

import Account.Account;

public class PaymentSystemFactory 
{
    public static PaymentSystem getPaymentSystem(PaymentController controller, Account account)
    {
        PaymentSystem system = null;
        String accountEmail = account.getEmail();
        
        //Determine account type from email
        switch(accountEmail)
        {
            case "aaron@ul.ie": system = new MemberPaymentSystem(controller); break;
            
            default:            system = new NonMemberPaymentSystem(controller); break;
        }
        
        return system;
    }
}
