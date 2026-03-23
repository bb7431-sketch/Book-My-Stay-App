import java.util.*;

/**
 * UseCase7AddOnServiceSelection demonstrates business extensibility 
 * by supporting optional services for existing reservations.
 * 
 * It uses a Map<String, List<Service>> to model the one-to-many 
 * relationship between a reservation and its selected services.
 * 
 * @author Book My Stay Team
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v7.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize Add-On Service Manager
        // Key: Reservation ID (or Room ID), Value: List of selected Services
        Map<String, List<Service>> addOnManager = new HashMap<>();

        // 2. Define Available Services
        Service breakfast = new Service("Buffet Breakfast", 20.0);
        Service spa = new Service("Spa Treatment", 50.0);
        Service wifi = new Service("High-Speed WiFi", 10.0);
        Service airportShuttle = new Service("Airport Shuttle", 30.0);

        // 3. Simulate existing Reservation (from Use Case 6)
        String reservationId = "RM-SINGLE-ROOM-101"; // Alice's Room ID
        System.out.println("Guest checking in to Reservation: " + reservationId);

        // 4. Guest selects optional services
        System.out.println("Guest selecting add-on services...");
        List<Service> selectedServices = new ArrayList<>();
        selectedServices.add(breakfast);
        selectedServices.add(wifi);
        selectedServices.add(spa);

        // 5. Map services to the reservation
        addOnManager.put(reservationId, selectedServices);

        // 6. Display selection and calculate total additional cost
        System.out.println("\n--- Add-On Service Summary ---");
        System.out.println("Reservation ID: " + reservationId);
        
        List<Service> guestServices = addOnManager.get(reservationId);
        double totalAddOnCost = 0;

        if (guestServices != null && !guestServices.isEmpty()) {
            System.out.println("Selected Services:");
            for (Service s : guestServices) {
                System.out.println("- " + s);
                totalAddOnCost += s.getCost();
            }
        } else {
            System.out.println("No add-on services selected.");
        }

        System.out.println("-----------------------------");
        System.out.println("Total Additional Cost: $" + totalAddOnCost);
        System.out.println("-----------------------------");

        System.out.println("Service selection complete. Core booking state remains unchanged.");
    }
}
