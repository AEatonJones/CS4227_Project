package payment;

import Reservation.Reservation;

public class MemberPaymentSystem extends AbstractPaymentSystem
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
