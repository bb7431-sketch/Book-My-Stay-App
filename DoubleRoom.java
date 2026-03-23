/**
 * Concrete implementation of a Double Room.
 * 
 * @author Book My Stay Team
 * @version 2.0
 */
public class DoubleRoom extends Room {
    public DoubleRoom(double pricePerNight) {
        super("Double Room", "Double Bed", 2, pricePerNight);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Bed Type: " + getBedType());
        System.out.println("Max Occupancy: " + getMaxOccupancy());
        System.out.println("Price per Night: $" + getPricePerNight());
    }
}
