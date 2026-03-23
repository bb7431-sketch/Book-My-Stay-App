import java.util.HashMap;
import java.util.Map;

/**
 * RoomInventory manages the availability of different room types.
 * It uses a HashMap to store room types as keys and their corresponding 
 * available counts as values, providing a centralized "Single Source of Truth".
 * 
 * @author Book My Stay Team
 * @version 3.0
 */
public class RoomInventory {
    // HashMap to store centralized availability mapping
    // String: Room Type, Integer: Available Count
    private Map<String, Integer> inventory;

    /**
     * Initializes the inventory with an empty map.
     */
    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    /**
     * Registers or updates a room type with its available count.
     * 
     * @param roomType the identifier for the room type
     * @param count the number of available rooms
     */
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    /**
     * Retrieves the current available count for a specific room type.
     * 
     * @param roomType the identifier for the room type
     * @return the number of available rooms, or 0 if not found
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Displays the current state of the entire inventory.
     */
    public void displayInventory() {
        System.out.println("Current Room Inventory Status:");
        System.out.println("-----------------------------");
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
            }
        }
    }
}
