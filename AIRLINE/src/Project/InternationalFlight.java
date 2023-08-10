package Project;

import java.util.Date;

public class InternationalFlight extends Flight {
    public InternationalFlight(String flightNumber, String destination, int seats, Date
            departureTime, Date arrivalTime, int duration) {
        super(flightNumber, destination, seats, departureTime, arrivalTime, duration);
    }

    @Override
    public double getTicketPrice() {
        // Calculate ticket price based on flight duration and additional fees
        return 200.00 + (getDuration() * 0.2);
    }
}
