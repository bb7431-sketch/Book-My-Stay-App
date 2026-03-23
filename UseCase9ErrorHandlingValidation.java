import java.util.*;

/**
 * UseCase9ErrorHandlingValidation strengthens system reliability 
 * by introducing structured validation and error handling.
 * 
 * It ensures that invalid inputs and inconsistent states are 
 * detected and handled early using custom exceptions.
 * 
 * @author Book My Stay Team
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v9.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.updateAvailability("Single Room", 1); // Only 1 available
        inventory.updateAvailability("Suite Room", 2);

        // 2. Define Test Cases (Name, Requested Type)
        String[][] testRequests = {
            {"Alice", "Single Room"},    // Valid
            {"Bob", "single room"},        // Invalid (Case-sensitive check)
            {"Charlie", "Luxury Villa"}, // Invalid (Non-existent type)
            {"David", "Single Room"},     // Invalid (Out of stock)
            {"Eve", "Suite Room"}          // Valid
        };

        System.out.println("Starting Booking Validation Process...\n");

        // 3. Process Requests with Error Handling
        for (String[] request : testRequests) {
            String guest = request[0];
            String type = request[1];

            System.out.println("Processing: " + guest + " -> " + type);

            try {
                // Fail-Fast Validation
                validateBooking(guest, type, inventory);

                // If valid, proceed with booking
                int available = inventory.getAvailability(type);
                inventory.updateAvailability(type, available - 1);
                System.out.println("SUCCESS: Booking confirmed for " + guest);

            } catch (InvalidBookingException e) {
                // Graceful Failure Handling
                System.out.println("VALIDATION FAILED: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("SYSTEM ERROR: An unexpected error occurred.");
            }
            System.out.println();
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Final Inventory Status:");
        inventory.displayInventory();
        System.out.println("\nValidation process complete. System remains stable.");
    }

    /**
     * Performs multi-step validation for a booking request.
     * 
     * @param guest current guest name
     * @param type requested room type
     * @param inventory current system inventory
     * @throws InvalidBookingException if any validation rule is violated
     */
    private static void validateBooking(String guest, String type, RoomInventory inventory) throws InvalidBookingException {
        // Rule 1: Room Type Presence (Case-Sensitive)
        // We simulate this by checking if the type exists in a known list or map
        // Note: RoomInventory.getAvailability(type) returns -1 if not found in some cases, 
        // but here it returns 0 if not initialized. 
        // Let's assume for validation that we only allow types already in inventory.
        
        // In this implementation, inventory.getAvailability returns 0 if never set.
        // For demonstration, let's explicitly check if the type is "Single Room" or "Suite Room".
        List<String> validTypes = Arrays.asList("Single Room", "Double Room", "Suite Room");
        
        if (!validTypes.contains(type)) {
            throw new InvalidBookingException("Invalid Room Type: '" + type + "'. (Note: Room types are case-sensitive)");
        }

        // Rule 2: Availability Guard
        int available = inventory.getAvailability(type);
        if (available <= 0) {
            throw new InvalidBookingException("No availability for " + type);
        }
    }
}
