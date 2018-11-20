package Extras;

public class Gym implements Visitable{

    private int time;
    private double price;
    
    public Gym(int hours){
        time = hours;
    }
    
    @Override
    public double accept(Visitor visitor) {
        visitor.visit(this);
        return price;
    }

    public int getHours()
    {
        return time;
    }
    
}
