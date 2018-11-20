package reservation;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReservationVisitorTest
{
    @Test
    public void getVisitTest() throws Exception
    {
        ReservationVisitor visitor = ReservationVisitor.getInstance();
        
        Reservation londonReservation   = Reservation.makeReservation("test@test.ie", "1/1/2018", 1, "London", "101");
        Reservation parisReservation    = Reservation.makeReservation("test@test.ie", "1/1/2018", 1, "Paris", "101");
        
        //Test getBookings
        assertEquals(0, visitor.getBookings("London"));
        assertEquals(0, visitor.getBookings("Paris"));
        
        //Test london reservation
        visitor.visit(londonReservation);
        assertEquals(1, visitor.getBookings("London"));
        assertEquals(0, visitor.getBookings("Paris"));
        
        //Test paris reservation
        visitor.visit(parisReservation);
        assertEquals(1, visitor.getBookings("London"));
        assertEquals(1, visitor.getBookings("Paris"));
        
        //Test singleton
        visitor = ReservationVisitor.getInstance();
        assertEquals(1, visitor.getBookings("London"));
        assertEquals(1, visitor.getBookings("Paris"));
    }
}
