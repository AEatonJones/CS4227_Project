package payment;

import account.Account;

public class PaymentSystemFactory 
{
    private PaymentSystemFactory() {
        //made to stop object creation
    }
        
    public static PaymentSystem getPaymentSystem(PaymentController controller, Account account)
    {
        PaymentSystem system;
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
