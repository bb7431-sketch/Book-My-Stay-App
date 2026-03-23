import java.util.*;

/**
 * UseCase8BookingHistoryReport introduces historical tracking of 
 * confirmed bookings for operational visibility and reporting.
 * 
 * It uses a List<Reservation> to maintain records in insertion order,
 * reinforcing a persistence-oriented mindset.
 * 
 * @author Book My Stay Team
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v8.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize System Components
        RoomInventory inventory = new RoomInventory();
        inventory.updateAvailability("Single Room", 5);
        inventory.updateAvailability("Suite Room", 2);

        Queue<Reservation> requestQueue = new LinkedList<>();
        requestQueue.add(new Reservation("Alice", "Single Room"));
        requestQueue.add(new Reservation("Bob", "Suite Room"));
        requestQueue.add(new Reservation("Charlie", "Single Room"));

        // 2. Initialize Booking History (Chronological Storage)
        List<Reservation> bookingHistory = new ArrayList<>();

        System.out.println("Processing and Recording Bookings...\n");

        // 3. Process Requests and Store in History
        while (!requestQueue.isEmpty()) {
            Reservation request = requestQueue.poll();
            String type = request.getRoomType();
            int available = inventory.getAvailability(type);

            if (available > 0) {
                // Confirmation logic (simplified for Use Case 8 focus)
                inventory.updateAvailability(type, available - 1);
                
                // Add to Historical Tracking
                bookingHistory.add(request);
                
                System.out.println("CONFIRMED: " + request.getGuestName() + " for " + type);
            } else {
                System.out.println("FAILED: No availability for " + request.getGuestName() + " (" + type + ")");
            }
        }

        System.out.println("\n--------------------------------------------------");
        
        // 4. Booking Report Service (Read-Only Analysis)
        generateBookingReport(bookingHistory);
        
        System.out.println("--------------------------------------------------");
        System.out.println("Operational reporting complete.");
    }

    /**
     * Generates a summary report from the booking history.
     * This method demonstrates separation of data storage and reporting.
     * 
     * @param history the list of confirmed reservations
     */
    private static void generateBookingReport(List<Reservation> history) {
        System.out.println("--- OFFICIAL BOOKING HISTORY REPORT ---");
        System.out.println("Total Confirmed Bookings: " + history.size());
        System.out.println();

        if (history.isEmpty()) {
            System.out.println("No booking records found.");
            return;
        }

        // Summary counts
        Map<String, Integer> summary = new HashMap<>();
        
        System.out.println("Detailed Transaction Log (In Order):");
        for (int i = 0; i < history.size(); i++) {
            Reservation res = history.get(i);
            System.out.println((i + 1) + ". " + res);
            
            // Aggregate data for reporting
            summary.put(res.getRoomType(), summary.getOrDefault(res.getRoomType(), 0) + 1);
        }

        System.out.println("\nRoom Type Summary:");
        for (Map.Entry<String, Integer> entry : summary.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " bookings");
        }
    }
}
