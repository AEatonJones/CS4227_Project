package extras;

public class Spa implements Visitable{

    private int time;
    
    public Spa(int hours){
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
