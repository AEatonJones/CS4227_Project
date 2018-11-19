package Extras;

public class ExtraDiscountVisitor implements Visitor {

    public void visit(Spa spa) {
       int hours = spa.getHours();
       System.out.println(hours + " hours spent in spa costing €" + hours * 25);
    }
    
    public void visit(PoolRoom poolRoom) {
       int hours = poolRoom.getHours();
       System.out.println(hours + " hours spent in pool room costing €" + hours * 1);
    }
    
    public void visit(SwimmingPool pool) {
       int hours = pool.getHours();
       System.out.println(hours + " hours spent in swimming pool costing €" + hours * 2.5);
    }
    
    public void visit(Gym gym) {
        int hours = gym.getHours();
        System.out.println(hours + " hours spent in gym costing €" + hours * 3.5);
    }
    
    public void visit(FunctionRoom functionRoom) {
        int hours = functionRoom.getHours();
        System.out.println(hours + " hours spent in function room costing €" + hours * 30);
    }
    
    public void visit(ConferenceRoom conferenceRoom) {
        int hours = conferenceRoom.getHours();
        System.out.println(hours + " hours spent in conference room costing €" + hours * 32.5);
    }
}

