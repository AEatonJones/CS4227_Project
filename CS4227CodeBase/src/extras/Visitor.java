package extras;

public interface Visitor {
    public void visit(Spa spa);
    public void visit(PoolRoom poolRoom);
    public void visit(SwimmingPool swimmingPool);
    public void visit(Gym gym);
    public void visit(FunctionRoom functionRoom);
    public void visit(ConferenceRoom conferenceRoom);
}
