package payment;

import reservation.Reservation;

public class MemberPaymentSystem extends PaymentSystem
{
    public MemberPaymentSystem(PaymentController controller)
    {
        super(controller);
    }
    
    @Override
    public float calculateCost(Reservation reservation)
    {
        return super.calculateCost(reservation) * .95f;
    }
}
