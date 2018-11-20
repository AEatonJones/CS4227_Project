package Extras;

public class PoolRoom implements Visitable{

    private int time;
    private double price;
    
    public PoolRoom(int hours){
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
