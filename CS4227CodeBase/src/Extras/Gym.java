package Extras;

public class Gym implements Visitable{

    private int time;
    
    public Gym(int hours){
        time = hours;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getHours()
    {
        return time;
    }
    
}
