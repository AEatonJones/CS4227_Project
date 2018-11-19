package Extras;

public class FunctionRoom implements Visitable{

    private int time;
    
    public FunctionRoom(int hours){
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

