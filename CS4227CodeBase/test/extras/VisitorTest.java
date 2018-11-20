package extras;

public class VisitorTest {
   public static void main(String[]args){
       
       ExtraVisitor vis = new ExtraVisitor();
       ExtraDiscountVisitor disvis = new ExtraDiscountVisitor();
       
       Spa spa = new Spa(4);
       PoolRoom poolRoom = new PoolRoom(1);
       SwimmingPool swim = new SwimmingPool(1);
       Gym gym = new Gym(2);
       FunctionRoom functionRoom = new FunctionRoom(7);
       ConferenceRoom conferenceRoom = new ConferenceRoom(10);
       
       spa.accept(vis);
       poolRoom.accept(vis);
       swim.accept(vis);
       gym.accept(vis);
       functionRoom.accept(vis);
       conferenceRoom.accept(vis);
       
       spa.accept(disvis);
       poolRoom.accept(disvis);
       swim.accept(disvis);
       gym.accept(disvis);
       functionRoom.accept(disvis);
       conferenceRoom.accept(disvis);
   } 
}
