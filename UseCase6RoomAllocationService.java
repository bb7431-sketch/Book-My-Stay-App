import java.util.*;

/**
 * UseCase6RoomAllocationService processes queued booking requests 
 * and performs room allocation safely.
 * 
 * It ensures:
 * 1. FIFO processing of requests.
 * 2. Unique room ID generation and assignment using a Set.
 * 3. Immediate inventory synchronization.
 * 
 * @author Book My Stay Team
 * @version 6.0
 */
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v6.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize Inventory Service
        RoomInventory inventory = new RoomInventory();
        inventory.updateAvailability("Single Room", 2);
        inventory.updateAvailability("Suite Room", 1);

        // 2. Initialize Booking Request Queue (from Use Case 5)
        Queue<Reservation> requestQueue = new LinkedList<>();
        requestQueue.add(new Reservation("Alice", "Single Room"));
        requestQueue.add(new Reservation("Bob", "Suite Room"));
        requestQueue.add(new Reservation("Charlie", "Single Room"));
        requestQueue.add(new Reservation("David", "Single Room")); // Will be rejected (out of stock)

        // 3. Track Allocated Rooms (to prevent double-booking)
        // Key: Room Type, Value: Set of unique Room IDs
        Map<String, Set<String>> allocatedRooms = new HashMap<>();
        allocatedRooms.put("Single Room", new HashSet<>());
        allocatedRooms.put("Double Room", new HashSet<>());
        allocatedRooms.put("Suite Room", new HashSet<>());

        System.out.println("Starting Room Allocation Process...\n");

        // 4. Process Requests in FIFO order
        while (!requestQueue.isEmpty()) {
            Reservation request = requestQueue.poll();
            String guest = request.getGuestName();
            String type = request.getRoomType();

            System.out.println("Processing request for " + guest + " (" + type + ")...");

            // Check availability
            int available = inventory.getAvailability(type);
            if (available > 0) {
                // Generate a Unique Room ID
                // In a real system, this might come from a database. 
                // Here we generate one based on the current size of the allocated set.
                String roomId = "RM-" + type.toUpperCase().replace(" ", "-") + "-" + (allocatedRooms.get(type).size() + 101);

                // Uniqueness Enforcement using Set
                if (!allocatedRooms.get(type).contains(roomId)) {
                    // Record the allocation
                    allocatedRooms.get(type).add(roomId);
                    
                    // Synchronize with Inventory
                    inventory.updateAvailability(type, available - 1);

                    System.out.println("SUCCESS: Room allocated to " + guest);
                    System.out.println("Assigned Room ID: " + roomId);
                    System.out.println("Remaining " + type + "s: " + (available - 1));
                } else {
                    // This block handles potential collisions if ID generation was randomized
                    System.out.println("ERROR: Internal Room ID collision detected. Please retry.");
                }
            } else {
                System.out.println("REJECTED: No availability for " + type);
            }
            System.out.println();
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Final Inventory Status:");
        inventory.displayInventory();
        
        System.out.println("\nFinal Allocation Summary:");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + " Assignments: " + entry.getValue());
        }
    }
}
