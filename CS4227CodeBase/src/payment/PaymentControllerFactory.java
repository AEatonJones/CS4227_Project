package payment;

import reservation.Reservation;

public class PaymentControllerFactory
{
    private PaymentControllerFactory() {
        //made to stop object creation
    }
    
    public static PaymentController getPaymentController(Reservation res)
    {
        PaymentController controller = null;
        
        switch(res.getLocation())
        {
            case "London":  controller = new BritishPaymentController();    break;
            case "Paris":   controller = new FrenchPaymentController();     break;
            default: controller = new FrenchPaymentController(); break;
        }
        
        return controller;
    }
}
