package payment;

import Reservation.Reservation;

public abstract class AbstractPaymentSystem
{
    private PaymentController payment;
    
    public AbstractPaymentSystem(PaymentController controller)
    {
        this.payment = payment;
    }
    
    public float calculateCost(Reservation reservation)
    {
        return (payment.getCostPlusTax(80.0f) * reservation.getNoNights());
    }
}
