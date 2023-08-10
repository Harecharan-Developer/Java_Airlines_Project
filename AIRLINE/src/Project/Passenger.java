package Project;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private String name;
    private String email;
    private String phone;
    private boolean member;
    private List<Flight> flights;

    public Passenger(String name, String email, String phone, boolean member) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.member = member;
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public double getTotalCost() {
        double total = 0.0;
        for (Flight flight : flights) {
            total += flight.getTicketPrice();
        }
        if (member) {
            total *= 0.9; // Apply 10% discount for members
        }
        return total;
    }
}
