package payment;

public abstract class PaymentController 
{
    protected float conversionRate;
    protected float taxRate;
    
    public float getCostPlusTax(float cost)
    {
        float convertedCost = convert(cost);
        
        return convertedCost + getTax(convertedCost);
    }
    
    protected float convert(float amount)
    {
        return amount * conversionRate;
    }
    
    protected float getTax(float amount)
    {
        return amount * taxRate;
    }
}
