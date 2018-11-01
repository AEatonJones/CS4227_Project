package payment;

public class PaymentControllerFactory 
{
    public static PaymentController getPaymentController(String location)
    {
        PaymentController controller = null;
        
        switch(location)
        {
            case "London":  controller = new BritishPaymentController();    break;
            case "Paris":   controller = new FrenchPaymentController();     break;
        }
        
        return controller;
    }
}
