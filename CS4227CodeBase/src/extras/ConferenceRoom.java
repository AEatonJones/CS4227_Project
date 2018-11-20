package extras;

public class ConferenceRoom implements Visitable{

    private int time;
    
    public ConferenceRoom(int hours){
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
