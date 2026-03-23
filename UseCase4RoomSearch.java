import java.util.ArrayList;
import java.util.List;

/**
 * UseCase4RoomSearch demonstrates safe, read-only room search functionality.
 * It reinforces the separation of concerns by isolating search logic from 
 * inventory mutation and booking logic.
 * 
 * @author Book My Stay Team
 * @version 4.0
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v4.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize System Components
        RoomInventory inventory = new RoomInventory();
        
        // Define room domain objects
        Room single = new SingleRoom(100.0);
        Room doubleRm = new DoubleRoom(150.0);
        Room suite = new SuiteRoom(300.0);

        // 2. Setup initial inventory state (Write Access)
        System.out.println("Initializing Inventory state...");
        inventory.updateAvailability("Single Room", 5);
        inventory.updateAvailability("Double Room", 0); // Out of stock
        inventory.updateAvailability("Suite Room", 2);
        System.out.println();

        // 3. Perform Room Search (Read-Only Access)
        System.out.println("--- Guest initiating room search ---");
        
        // List of all room types we want to check
        List<Room> roomTypes = new ArrayList<>();
        roomTypes.add(single);
        roomTypes.add(doubleRm);
        roomTypes.add(suite);

        boolean foundAvailable = false;
        System.out.println("Available Rooms:");
        
        for (Room room : roomTypes) {
            String type = room.getRoomType();
            
            // Defensive Programming: Retrieve availability from centralized inventory
            int availableCount = inventory.getAvailability(type);
            
            // Validation Logic: Only display rooms with availability > 0
            if (availableCount > 0) {
                System.out.println("-----------------------------");
                room.displayRoomDetails();
                System.out.println("Status: " + availableCount + " rooms available");
                foundAvailable = true;
            } else {
                // Defensive Logic: Skipping unavailable room type
                // System.out.println("Skipping " + type + " (Current Availability: 0)");
            }
        }

        if (!foundAvailable) {
            System.out.println("No rooms are currently available.");
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Search operation complete. System state remains unchanged.");
    }
}
