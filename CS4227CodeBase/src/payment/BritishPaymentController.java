package payment;

public class BritishPaymentController extends PaymentController
{
    private static final float HOSP_CHARGE = 7.5f;
    
    public BritishPaymentController()
    {
        this.conversionRate = .83f;
        this.taxRate = .05f;
    }
    
    public float getTax()
    {
        return super.getTax(taxRate) + HOSP_CHARGE;
    }
}
