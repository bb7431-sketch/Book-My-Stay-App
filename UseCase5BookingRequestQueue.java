import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue demonstrates fair booking request handling 
 * by introducing a request intake mechanism that preserves arrival order.
 * 
 * @author Book My Stay Team
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v5.0!");
        System.out.println("--------------------------------------------------");

        // 1. Initialize the Booking Request Queue
        // Using LinkedList as the Queue implementation (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        System.out.println("Processing incoming booking requests (Intake Phase)...");

        // 2. Guest submissions (Simulating arrival over time)
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        System.out.println("Added: Alice's request for Single Room");
        
        bookingQueue.add(new Reservation("Bob", "Suite Room"));
        System.out.println("Added: Bob's request for Suite Room");
        
        bookingQueue.add(new Reservation("Charlie", "Single Room"));
        System.out.println("Added: Charlie's request for Single Room");
        
        System.out.println();

        // 3. Display the queue to verify FIFO ordering
        System.out.println("Current Booking Request Queue:");
        System.out.println("-----------------------------");
        if (bookingQueue.isEmpty()) {
            System.out.println("The queue is empty.");
        } else {
            int position = 1;
            for (Reservation res : bookingQueue) {
                System.out.println(position + ". " + res);
                position++;
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Request intake complete. Requests are ready for fair processing.");
        System.out.println("No inventory was modified during this phase.");
    }
}
