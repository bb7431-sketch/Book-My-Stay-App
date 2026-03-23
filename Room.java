/**
 * Abstract class representing a general Room in the Hotel Booking System.
 * Defines common attributes and behavior shared by all room types.
 * 
 * @author Book My Stay Team
 * @version 2.0
 */
public abstract class Room {
    private String roomType;
    private String bedType;
    private int maxOccupancy;
    private double pricePerNight;

    /**
     * Constructor to initialize common room attributes.
     * 
     * @param roomType the identifier for the room type
     * @param bedType the type of bed in the room
     * @param maxOccupancy the maximum number of guests allowed
     * @param pricePerNight the cost per night for the room
     */
    public Room(String roomType, String bedType, int maxOccupancy, double pricePerNight) {
        this.roomType = roomType;
        this.bedType = bedType;
        this.maxOccupancy = maxOccupancy;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Abstract method to display specific room details.
     * Must be implemented by concrete subclasses.
     */
    public abstract void displayRoomDetails();

    // Getters for encapsulation
    public String getRoomType() { return roomType; }
    public String getBedType() { return bedType; }
    public int getMaxOccupancy() { return maxOccupancy; }
    public double getPricePerNight() { return pricePerNight; }
}
