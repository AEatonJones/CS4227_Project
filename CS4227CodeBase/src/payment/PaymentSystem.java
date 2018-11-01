package payment;

import Reservation.Reservation;

public abstract class PaymentSystem
{
    private PaymentController payment;
    
    public PaymentSystem(PaymentController payment)
    {
        this.payment = payment;
    }
    
    public float calculateCost(Reservation reservation)
    {
        return (payment.getCostPlusTax(80.0f) * reservation.getNoNights());
    }
}
