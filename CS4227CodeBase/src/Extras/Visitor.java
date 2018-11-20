package Extras;

public interface Visitor {
    public double visit(Spa spa);
    public double visit(PoolRoom poolRoom);
    public double visit(SwimmingPool swimmingPool);
    public double visit(Gym gym);
    public double visit(FunctionRoom functionRoom);
    public double visit(ConferenceRoom conferenceRoom);
}
