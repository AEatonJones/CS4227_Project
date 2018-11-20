package payment;

import reservation.Reservation;

public class NonMemberPaymentSystem extends PaymentSystem
{
    public NonMemberPaymentSystem(PaymentController paymentController)
    {
        super(paymentController);
    }
    
    @Override
    public float calculateCost(Reservation reservation)
    {
        return (super.calculateCost(reservation)) + 7.0f;
    }
}
