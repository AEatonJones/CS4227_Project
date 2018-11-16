package hotel.room;

public interface RoomVisitor {
    public void visit(BedRoom bedroom);
    public void visit(Gym gym);
    public void visit(FunctionRoom functionRoom);
}
