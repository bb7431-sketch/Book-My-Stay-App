import java.util.*;
import java.util.concurrent.*;

/**
 * UseCase11ConcurrentBookingSimulation demonstrates how concurrent access 
 * to shared resources is handled using synchronization.
 * 
 * It simulates multiple guests submitting booking requests simultaneously 
 * and ensures that inventory updates and room allocations are thread-safe.
 * 
 * @author Book My Stay Team
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {

    // Shared Shared Resources
    private static final RoomInventory inventory = new RoomInventory();
    private static final Queue<Reservation> sharedQueue = new LinkedList<>();
    
    // Lock for synchronizing queue and inventory access
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to the Hotel Booking Management System v11.0!");
        System.out.println("Thread-Safe Concurrent Simulation Started...");
        System.out.println("--------------------------------------------------");

        // 1. Initialize Inventory with limited rooms
        inventory.updateAvailability("Single Room", 3);
        System.out.println("Initial Inventory: 3 Single Rooms available.");

        // 2. Prepare Guest Requests (Multithreaded intake)
        int guestCount = 6; // More guests than rooms to test overbooking safety
        ExecutorService executor = Executors.newFixedThreadPool(guestCount);
        
        System.out.println("\nSimulating " + guestCount + " guests submitting requests simultaneously...");

        for (int i = 1; i <= guestCount; i++) {
            final String guestName = "Guest-" + i;
            executor.execute(() -> {
                Reservation request = new Reservation(guestName, "Single Room");
                synchronized (lock) {
                    sharedQueue.add(request);
                    System.out.println("[INTAKE] " + guestName + " added request to the shared queue.");
                }
            });
        }

        // Wait for all intake threads to finish
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\nQueue Intake Complete. Current Queue Size: " + sharedQueue.size());
        System.out.println("--------------------------------------------------");

        // 3. Process Requests Concurrently (Thread-Safe Allocation)
        System.out.println("Processing allocations using multiple worker threads...\n");
        
        int processorCount = 3;
        ExecutorService processors = Executors.newFixedThreadPool(processorCount);

        for (int i = 0; i < processorCount; i++) {
            processors.execute(() -> {
                while (true) {
                    Reservation request = null;
                    
                    // Critical Section: Dequeue and Allocate
                    synchronized (lock) {
                        if (sharedQueue.isEmpty()) break;
                        request = sharedQueue.poll();
                        
                        if (request != null) {
                            String type = request.getRoomType();
                            int available = inventory.getAvailability(type);
                            
                            if (available > 0) {
                                // Thread-Safe Inventory Update
                                inventory.updateAvailability(type, available - 1);
                                System.out.println("[ALLOCATION] SUCCESS: " + request.getGuestName() + 
                                                   " secured a room. Remaining: " + (available - 1));
                            } else {
                                System.out.println("[ALLOCATION] REJECTED: No rooms left for " + request.getGuestName());
                            }
                        }
                    }
                    
                    // Simulate processing time
                    try { Thread.sleep(100); } catch (InterruptedException e) { break; }
                }
            });
        }

        // Wait for all processor threads to finish
        processors.shutdown();
        processors.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\n--------------------------------------------------");
        System.out.println("Final System Integrity Check:");
        inventory.displayInventory();
        
        System.out.println("--------------------------------------------------");
        System.out.println("Concurrent simulation complete. Synchronization successfully prevented overbooking.");
    }
}
