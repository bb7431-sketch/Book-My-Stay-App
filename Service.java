/**
 * Service represents an optional add-on offering for a reservation.
 * Examples include Breakfast, Spa, WiFi, etc.
 * 
 * @author Book My Stay Team
 * @version 7.0
 */
public class Service {
    private String serviceName;
    private double cost;

    /**
     * Initializes a new optional service.
     * 
     * @param serviceName the name of the service
     * @param cost the additional cost for the service
     */
    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    // Getters
    public String getServiceName() { return serviceName; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return serviceName + " ($" + cost + ")";
    }
}
