/**
 * UseCase2RoomInitialization demonstrates object modeling, inheritance, and polymorphism.
 * It initializes room objects and displays their properties along with static availability.
 * 
 * @author Book My Stay Team
 * @version 2.0
 */
public class UseCase2RoomInitialization {

    // Static availability representation (using simple variables as per requirements)
    private static int singleRoomAvailability = 10;
    private static int doubleRoomAvailability = 5;
    private static int suiteRoomAvailability = 2;

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System v2.0!");
        System.out.println("--------------------------------------------------");

        // Polymorphism: Using the Room reference to point to concrete objects
        Room single = new SingleRoom(100.0);
        Room doubleRm = new DoubleRoom(150.0);
        Room suite = new SuiteRoom(300.0);

        // Display Room Details and Availability
        System.out.println("Initializing Room Types...\n");

        single.displayRoomDetails();
        System.out.println("Current Availability: " + singleRoomAvailability);
        System.out.println();

        doubleRm.displayRoomDetails();
        System.out.println("Current Availability: " + doubleRoomAvailability);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Current Availability: " + suiteRoomAvailability);
        System.out.println();

        System.out.println("--------------------------------------------------");
        System.out.println("Room initialization complete.");
    }
}
