/**
 * Reservation represents a guest's intent to book a room.
 * It serves as a data holder for booking requests before they are processed.
 * 
 * @author Book My Stay Team
 * @version 5.0
 */
public class Reservation {
    private String guestName;
    private String roomType;

    /**
     * Initializes a new booking reservation request.
     * 
     * @param guestName the name of the guest
     * @param roomType the requested room type
     */
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    // Getters for encapsulation
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room Type: " + roomType + "]";
    }
}
