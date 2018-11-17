package hotel.room;

public class ReservationVisitor implements RoomVisitor {
    
    private int londonCount;
    private int parisCount;
    private float londonMoney;
    private float parisMoney;
    
    public ReservationVisitor(){
        londonCount = 0;
        parisCount = 0;
        londonMoney = 0;
        parisMoney = 0;
    }

    @Override
    public void visit(BedRoom bedroom) {
        String location = bedroom.getHotelLocation();
        if(location.equalsIgnoreCase("London"))
        {
            londonCount++;
            londonMoney += bedroom.getCost();
        }
        else if(location.equalsIgnoreCase("Paris"))
        {
            parisCount++;
            parisMoney += bedroom.getCost();
        }
        
    }
    
    public void visit(IRoom room){
        System.out.println(londonCount + " rooms booked in London, earning €" + londonMoney + "\n" + parisCount + " rooms in Paris, earing €" + parisMoney);
    }
    
}
