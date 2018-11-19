package Extras;

public class ExtraVisitor implements Visitor {

    public void visit(Spa spa) {
       int hours = spa.getHours();
       System.out.println(hours + " hours spent in spa costing €" + hours * 50);
    }
    
    public void visit(PoolRoom poolRoom) {
       int hours = poolRoom.getHours();
       System.out.println(hours + " hours spent in pool room costing €" + hours * 2);
    }
    
    public void visit(SwimmingPool pool) {
       int hours = pool.getHours();
       System.out.println(hours + " hours spent in swimming pool costing €" + hours * 5);
    }
    
    public void visit(Gym gym) {
        int hours = gym.getHours();
        System.out.println(hours + " hours spent in gym costing €" + hours * 7);
    }
    
    public void visit(FunctionRoom functionRoom) {
        int hours = functionRoom.getHours();
        System.out.println(hours + " hours spent in function room costing €" + hours * 60);
    }
    
    public void visit(ConferenceRoom conferenceRoom) {
        int hours = conferenceRoom.getHours();
        System.out.println(hours + " hours spent in conference room costing €" + hours * 65);
    }
}
