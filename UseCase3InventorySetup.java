/**
 * UseCase3InventorySetup demonstrates the transition to a centralized 
 * inventory management system using HashMap.
 * 
 * @author Book My Stay Team
 * @version 3.0
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v3.0!");
        System.out.println("--------------------------------------------------");

        // Initialize the centralized RoomInventory
        RoomInventory inventory = new RoomInventory();

        // Registering room types with their availability counts
        System.out.println("Registering room types in centralized inventory...");
        inventory.updateAvailability("Single Room", 10);
        inventory.updateAvailability("Double Room", 5);
        inventory.updateAvailability("Suite Room", 2);

        // Display current inventory state
        inventory.displayInventory();
        System.out.println();

        // Demonstrate a retrieval operation
        String searchType = "Double Room";
        System.out.println("Checking availability for: " + searchType);
        System.out.println("Available: " + inventory.getAvailability(searchType));
        System.out.println();

        // Demonstrate a controlled update
        System.out.println("Updating availability for Suite Room (1 room booked)...");
        int currentSuites = inventory.getAvailability("Suite Room");
        inventory.updateAvailability("Suite Room", currentSuites - 1);

        // Display updated inventory state
        inventory.displayInventory();

        System.out.println("--------------------------------------------------");
        System.out.println("Inventory setup and demonstration complete.");
    }
}
