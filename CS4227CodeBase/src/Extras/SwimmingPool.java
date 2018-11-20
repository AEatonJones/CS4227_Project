package Extras;

public class SwimmingPool implements Visitable{

    private int time;
    private double price;
    
    public SwimmingPool(int hours){
        time = hours;
    }
    
    public double accept(Visitor visitor) {
        visitor.visit(this);
        return price;
    }

    public int getHours()
    {
        return time;
    }
    
}
