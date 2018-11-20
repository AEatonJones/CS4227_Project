package hotel.room;

public abstract class Room implements IRoom {
    protected int id;
    protected String hotelLocation;
    protected int noOfSingleBeds;
    protected int noOfDoubleBeds;
    protected float cost;
    
    
    @Override
    public int getID(){
        return id;
    }
    
    @Override
    public String getHotelLocation(){
        return hotelLocation;
    }
    
    @Override
    public int getSingleBeds(){
        return noOfSingleBeds;
    }
    
    @Override
    public int getDoubleBeds(){
        return noOfDoubleBeds;
    }
    
    @Override
    public float getCost(){
        return cost;
    }
    
    public Room(int id, String hotelLocation, int noOfSingleBeds, int noOfDoubleBeds, float cost){
        this.id = id;
        this.hotelLocation = hotelLocation;
        this.noOfSingleBeds = noOfSingleBeds;
        this.noOfDoubleBeds = noOfDoubleBeds;
        this.cost = cost;
    }
        
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
