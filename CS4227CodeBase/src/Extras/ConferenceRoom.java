package Extras;

public class ConferenceRoom implements Visitable{

    private int time;
    private double price;
    
    public ConferenceRoom(int hours){
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
