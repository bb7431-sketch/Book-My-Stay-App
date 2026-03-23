import java.io.Serializable;

/**
 * Reservation represents a guest's intent to book a room.
 * It is now Serializable to support data persistence.
 * 
 * @author Book My Stay Team
 * @version 12.0
 */
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
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
