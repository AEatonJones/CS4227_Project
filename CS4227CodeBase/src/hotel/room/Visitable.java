package hotel.room;

public interface Visitable {
    public void acceptRoomVisitor(RoomVisitor visitor);
}
