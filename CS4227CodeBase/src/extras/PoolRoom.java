package extras;

public class PoolRoom implements Visitable{

    private int time;
    
    public PoolRoom(int hours){
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
