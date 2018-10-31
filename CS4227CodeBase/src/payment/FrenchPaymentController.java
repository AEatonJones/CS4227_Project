package payment;

public class FrenchPaymentController extends PaymentController
{
    public FrenchPaymentController()
    {
        this.conversionRate = 1.0f;
        this.taxRate = 20.0f;
    }
}
