import java.util.*;

/**
 * UseCase10BookingCancellation enables safe cancellation of confirmed bookings 
 * by correctly reversing system state changes.
 * 
 * It uses a Stack<String> to track recently released room IDs, 
 * demonstrating LIFO rollback behavior.
 * 
 * @author Book My Stay Team
 * @version 10.0
 */
public class UseCase10BookingCancellation {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v10.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize System Components
        RoomInventory inventory = new RoomInventory();
        inventory.updateAvailability("Single Room", 5);

        // Track active bookings: Guest Name -> Room ID
        Map<String, String> activeBookings = new HashMap<>();
        
        // Stack to track released room IDs chronologically (LIFO)
        Stack<String> releasedRoomIds = new Stack<>();

        System.out.println("Simulating Booking Confirmations...");
        confirmBooking("Alice", "Single Room", inventory, activeBookings);
        confirmBooking("Bob", "Single Room", inventory, activeBookings);
        confirmBooking("Charlie", "Single Room", inventory, activeBookings);
        
        System.out.println("\nCurrent System State:");
        System.out.println("Active Bookings: " + activeBookings);
        inventory.displayInventory();

        System.out.println("\n--------------------------------------------------");
        System.out.println("Processing Cancellation Requests...");

        // 2. Perform Cancellations
        cancelBooking("Bob", inventory, activeBookings, releasedRoomIds);
        cancelBooking("Alice", inventory, activeBookings, releasedRoomIds);
        
        // Attempt an invalid cancellation
        cancelBooking("David", inventory, activeBookings, releasedRoomIds);

        System.out.println("\n--------------------------------------------------");
        System.out.println("Rollback Tracking Summary:");
        System.out.println("Released Room IDs Stack (Top is most recent): " + releasedRoomIds);
        
        if (!releasedRoomIds.isEmpty()) {
            System.out.println("Last room ID released (LIFO): " + releasedRoomIds.peek());
        }

        System.out.println("\nFinal System State:");
        System.out.println("Active Bookings: " + activeBookings);
        inventory.displayInventory();

        System.out.println("--------------------------------------------------");
        System.out.println("Cancellation and rollback process complete.");
    }

    /**
     * Confirms a booking and assigns a room ID.
     */
    private static void confirmBooking(String guest, String type, RoomInventory inv, Map<String, String> active) {
        int available = inv.getAvailability(type);
        if (available > 0) {
            String roomId = "RM-" + type.toUpperCase().replace(" ", "-") + "-" + (active.size() + 101);
            inv.updateAvailability(type, available - 1);
            active.put(guest, roomId);
            System.out.println("CONFIRMED: " + guest + " assigned to " + roomId);
        }
    }

    /**
     * Cancels an existing booking and performs inventory rollback.
     * 
     * @param guest guest requesting cancellation
     * @param inv system inventory
     * @param active map of active bookings
     * @param rollbackStack stack to record released room IDs
     */
    private static void cancelBooking(String guest, RoomInventory inv, Map<String, String> active, Stack<String> rollbackStack) {
        System.out.println("Processing cancellation for: " + guest + "...");

        // 1. Validation - Ensure the reservation exists
        if (!active.containsKey(guest)) {
            System.out.println("REJECTED: No active booking found for " + guest);
            return;
        }

        // 2. Retrieve allocation details
        String roomId = active.remove(guest);
        
        // 3. Identify room type (In this simple case, we know it's "Single Room")
        String type = "Single Room";

        // 4. Record in rollback structure (LIFO)
        rollbackStack.push(roomId);

        // 5. Increment Inventory
        int current = inv.getAvailability(type);
        inv.updateAvailability(type, current + 1);

        System.out.println("SUCCESS: Booking cancelled for " + guest);
        System.out.println("Room ID " + roomId + " has been released back to the pool.");
    }
}
