package Extras;

public class FunctionRoom implements Visitable{

    private int time;
    private double price;
    
    public FunctionRoom(int hours){
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

