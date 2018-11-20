package Extras;

public class ExtraVisitor implements Visitor {

    public void visit(Spa spa) {
       int hours = spa.getHours();
       double price = hours * 50;
       System.out.println(hours + " hours spent in spa costing €" + price);
    }
    
    public void visit(PoolRoom poolRoom) {
       int hours = poolRoom.getHours();
       double price = hours * 2;
       System.out.println(hours + " hours spent in pool room costing €" + price);
    }
    
    public void visit(SwimmingPool pool) {
       int hours = pool.getHours();
       double price = hours * 5;
       System.out.println(hours + " hours spent in swimming pool costing €" + price);
    }
    
    public void visit(Gym gym) {
        int hours = gym.getHours();
        double price = hours * 7;
        System.out.println(hours + " hours spent in gym costing €" + price);
    }
    
    public void visit(FunctionRoom functionRoom) {
        int hours = functionRoom.getHours();
        double price = hours * 60;
        System.out.println(hours + " hours spent in function room costing €" + price);
    }
    
    public void visit(ConferenceRoom conferenceRoom) {
        int hours = conferenceRoom.getHours();
        double price = hours * 65;
        System.out.println(hours + " hours spent in conference room costing €" + price);
    }
}
