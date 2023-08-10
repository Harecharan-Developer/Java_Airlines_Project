package Project;

import java.sql.Time;
import java.util.Date;

public abstract class Flight {
    private String flightNumber;
    private String destination;

    public int getTotalflight() {
        return totalflight;
    }

    private static int totalflight=0;
    private int seats;
    private Date departureTime;
    private Date arrivalTime;
    private int duration;

    public Flight(String flightNumber, String destination, int seats, Date departureTime, Date
            arrivalTime, int duration) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.seats = seats;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        totalflight = totalflight+1;
    }

    public abstract double getTicketPrice();

    public boolean hasAvailableSeats() {
        return seats > 0;
    }

    public void bookSeat() {
        seats--;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public int getSeats() {
        return seats;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public int getDuration() {
        return duration;
    }
}
