/**
 * Concrete implementation of a Suite Room.
 * 
 * @author Book My Stay Team
 * @version 2.0
 */
public class SuiteRoom extends Room {
    public SuiteRoom(double pricePerNight) {
        super("Suite Room", "King Bed", 4, pricePerNight);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Bed Type: " + getBedType());
        System.out.println("Max Occupancy: " + getMaxOccupancy());
        System.out.println("Price per Night: $" + getPricePerNight());
    }
}
