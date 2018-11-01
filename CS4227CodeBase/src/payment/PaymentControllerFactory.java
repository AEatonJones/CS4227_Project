package payment;

import Reservation.*;

public class PaymentControllerFactory 
{
    public static PaymentController getPaymentController(Reservation res)
    {
        PaymentController controller = null;
        
        switch(res.getLocation())
        {
            case "London":  controller = new BritishPaymentController();    break;
            case "Paris":   controller = new FrenchPaymentController();     break;
        }
        
        return controller;
    }
}
