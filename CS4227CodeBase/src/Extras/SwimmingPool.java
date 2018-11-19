package Extras;

public class SwimmingPool implements Visitable{

    private int time;
    
    public SwimmingPool(int hours){
        time = hours;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getHours()
    {
        return time;
    }
    
}
