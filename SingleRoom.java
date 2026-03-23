/**
 * Concrete implementation of a Single Room.
 * 
 * @author Book My Stay Team
 * @version 2.0
 */
public class SingleRoom extends Room {
    public SingleRoom(double pricePerNight) {
        super("Single Room", "Single Bed", 1, pricePerNight);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Bed Type: " + getBedType());
        System.out.println("Max Occupancy: " + getMaxOccupancy());
        System.out.println("Price per Night: $" + getPricePerNight());
    }
}
