package Reservation;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.fail;

public class ReservationTest 
{
    private Reservation reservation = null;
    
    @Before
    @Test
    public void makeValidReservationTest(){
        
        try {
            reservation = Reservation.makeReservation("101", "10/11/12", 10,"Paris","101");
            if(reservation == null)
                fail("Reservation should not be null.");
        } catch(Exception e) {
            e.printStackTrace();
            fail("Should not throw exception.");
        }
    }
    
    @Test
    public void reservationToStringTest()
    {
        assertEquals("101,10/11/12,10,60.00\n", reservation.toString());
    }
}
