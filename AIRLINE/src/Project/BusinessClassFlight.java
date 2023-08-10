package Project;

import java.util.Date;

public class BusinessClassFlight extends Flight {
    public BusinessClassFlight(String flightNumber, String destination, int seats, Date
            departureTime, Date arrivalTime, int duration) {
        super(flightNumber, destination, seats, departureTime, arrivalTime, duration);
    }

    @Override
    public double getTicketPrice() {
        // Calculate ticket price based on flight duration and additional fees
        //Use getter and get the value of duration

        return 500.00 + (getDuration() * 0.5);
    }
}
