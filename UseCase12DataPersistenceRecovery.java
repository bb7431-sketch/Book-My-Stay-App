import java.io.*;
import java.util.*;

/**
 * UseCase12DataPersistenceRecovery introduces persistence and recovery concepts.
 * 
 * It ensures that critical system state (inventory and booking history) 
 * survives application restarts by serializing data to a file.
 * 
 * @author Book My Stay Team
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {

    private static final String PERSISTENCE_FILE = "hotel_state.ser";

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v12.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize and Update System State
        RoomInventory inventory = new RoomInventory();
        inventory.updateAvailability("Single Room", 10);
        inventory.updateAvailability("Suite Room", 5);

        List<Reservation> bookingHistory = new ArrayList<>();
        bookingHistory.add(new Reservation("Alice", "Single Room"));
        bookingHistory.add(new Reservation("Bob", "Suite Room"));

        System.out.println("Current In-Memory State:");
        System.out.println("- Bookings recorded: " + bookingHistory.size());
        inventory.displayInventory();

        // 2. Persist State to File
        System.out.println("\n--------------------------------------------------");
        System.out.println("Initiating System Shutdown & Persistence...");
        saveSystemState(inventory, bookingHistory);

        // 3. Simulate Total System Wipe (Memory Clear)
        System.out.println("\n[SYSTEM RESTART] Clearing all in-memory data...");
        inventory = null;
        bookingHistory = null;

        // 4. Recover State from File
        System.out.println("\nStarting System Recovery...");
        Object[] recoveredData = loadSystemState();

        if (recoveredData != null) {
            inventory = (RoomInventory) recoveredData[0];
            bookingHistory = (List<Reservation>) recoveredData[1];

            System.out.println("\n--------------------------------------------------");
            System.out.println("Recovery Successful!");
            System.out.println("Recovered Bookings count: " + bookingHistory.size());
            System.out.println("Restored Inventory:");
            inventory.displayInventory();
        } else {
            System.out.println("Recovery failed. Starting with fresh state.");
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Data persistence and recovery process complete.");
    }

    /**
     * Serializes the system state into a file.
     */
    private static void saveSystemState(RoomInventory inv, List<Reservation> history) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PERSISTENCE_FILE))) {
            oos.writeObject(inv);
            oos.writeObject(history);
            System.out.println("SUCCESS: System state serialized to " + PERSISTENCE_FILE);
        } catch (IOException e) {
            System.err.println("ERROR: Persistence failed: " + e.getMessage());
        }
    }

    /**
     * Deserializes the system state from a file.
     * 
     * @return an array containing recovered inventory and booking history
     */
    @SuppressWarnings("unchecked")
    private static Object[] loadSystemState() {
        File file = new File(PERSISTENCE_FILE);
        if (!file.exists()) {
            System.out.println("No persistence file found.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PERSISTENCE_FILE))) {
            RoomInventory inv = (RoomInventory) ois.readObject();
            List<Reservation> history = (List<Reservation>) ois.readObject();
            System.out.println("SUCCESS: System state loaded from " + PERSISTENCE_FILE);
            return new Object[]{inv, history};
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Recovery failed: " + e.getMessage());
            return null;
        }
    }
}
