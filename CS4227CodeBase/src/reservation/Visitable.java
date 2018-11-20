package reservation;

public interface Visitable {
    public void acceptRoomVisitor(Visitor visitor);
}
