package hotel.room;

public abstract class Room implements IRoom {
    protected int id;
    protected String hotel_location;
    protected int no_of_single_beds;
    protected int no_of_double_beds;
    protected float cost;
    
    private BedRoom bedroom;
    private Gym gym;
    private FunctionRoom functionRoom;
    
    @Override
    public int getID(){
        return id;
    }
    
    @Override
    public String getHotelLocation(){
        return hotel_location;
    }
    
    @Override
    public int getSingleBeds(){
        return no_of_single_beds;
    }
    
    @Override
    public int getDoubleBeds(){
        return no_of_double_beds;
    }
    
    @Override
    public float getCost(){
        return cost;
    }
    
    public Room(int id, String hotel_location, int no_of_single_beds, int no_of_double_beds, float cost){
        this.id = id;
        this.hotel_location = hotel_location;
        this.no_of_single_beds = no_of_single_beds;
        this.no_of_double_beds = no_of_double_beds;
        this.cost = cost;
    }
        
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
