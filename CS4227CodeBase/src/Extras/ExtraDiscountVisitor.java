package Extras;

public class ExtraDiscountVisitor implements Visitor {

    public void visit(Spa spa) {
       int hours = spa.getHours();
       double price = hours*25;
       System.out.println(hours + " hours spent in spa costing €" + price);
    }
    
    public void visit(PoolRoom poolRoom) {
       int hours = poolRoom.getHours();
       double price = hours*1;
       System.out.println(hours + " hours spent in pool room costing €" + price);
    }
    
    public void visit(SwimmingPool pool) {
       int hours = pool.getHours();
       double price = hours*2.5;
       System.out.println(hours + " hours spent in swimming pool costing €" + price);
    }
    
    public void visit(Gym gym) {
        int hours = gym.getHours();
        double price = hours*3.5;
        System.out.println(hours + " hours spent in gym costing €" + price);
    }
    
    public void visit(FunctionRoom functionRoom) {
        int hours = functionRoom.getHours();
        double price = hours*30;
        System.out.println(hours + " hours spent in function room costing €" + price);
    }
    
    public void visit(ConferenceRoom conferenceRoom) {
        int hours = conferenceRoom.getHours();
        double price = hours * 32.5;
        System.out.println(hours + " hours spent in conference room costing €" + price);
    }
}

