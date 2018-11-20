package reservation;

public class ReservationVisitor implements Visitor {
    
    private int     londonCount;
    private int     parisCount;
    
    private static ReservationVisitor instance = null;
    
    public static ReservationVisitor getInstance()
    {
        if(instance == null)
        {
            instance = new ReservationVisitor();
        }
        
        return instance;
    }
    
    private ReservationVisitor(){
        londonCount = 0;
        
        parisCount = 0;
    }
    
    public int getBookings(String location)
    {
        int bookings = -1;
        
        switch(location)
        {
            case "London":  bookings = londonCount; break;
            case "Paris":   bookings = parisCount;  break;
            default: bookings = parisCount; break;
        }
        
        return bookings;
    }

    @Override
    public void visit(Reservation reservation)
    {
        String location = reservation.getLocation();
        if(location.equalsIgnoreCase("London"))
        {
            londonCount++;
        }
        else if(location.equalsIgnoreCase("Paris"))
        {
            parisCount++;
        }
    }
}
