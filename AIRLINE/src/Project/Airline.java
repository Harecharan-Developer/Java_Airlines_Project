package Project;

import java.util.*;

public class Airline implements Runnable {
    public static List<Flight> flights;
    private PaymentService paymentService;

    public Airline() {

        flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {

        flights.add(flight);
        System.out.println("Flight added Successfully");

    }

    public void searchFlights_destination(String destination) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDestination().equals(destination)) {
                results.add(flight);
            }
        }
        for (Flight avlflight : results) {
            System.out.println("Flight Number : "+avlflight.getFlightNumber());
            System.out.println("Destination : "+avlflight.getDestination());
            System.out.println("Departure Date : "+avlflight.getDepartureTime());
            System.out.println("Arrival Date: "+avlflight.getArrivalTime());
            System.out.println("Duration : "+avlflight.getDuration());
        }
    }

    public void searchFlights_number(String flightNumber) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                results.add(flight);
            }
        }
        for (Flight avlflight : results) {
            System.out.println("Flight Number : "+avlflight.getFlightNumber());
            System.out.println("Destination : "+avlflight.getDestination());
            System.out.println("Departure Date : "+avlflight.getDepartureTime());
            System.out.println("Arrival Date: "+avlflight.getArrivalTime());
            System.out.println("Duration : "+avlflight.getDuration());
        }
    }


    public void searchFlights_minseats(int minseats) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getSeats() >= minseats) {
                results.add(flight);
            }
        }
        for (Flight avlflight : results) {
            System.out.println("Flight Number : "+avlflight.getFlightNumber());
            System.out.println("Destination : "+avlflight.getDestination());
            System.out.println("Departure Date : "+avlflight.getDepartureTime());
            System.out.println("Arrival Date: "+avlflight.getArrivalTime());
            System.out.println("Duration : "+avlflight.getDuration());
        }

    }

    public void bookFlight(Passenger passenger, Flight flight) {

        flight.bookSeat();
        passenger.addFlight(flight);
    }

    public void Payment(Flight flight){
        if (!flights.contains(flight)) {
            throw new IllegalArgumentException("Invalid flight");
        }
        if (!flight.hasAvailableSeats()) {
            throw new IllegalStateException("Flight is full");
        }
        System.out.println("Enter type of Payment");
        System.out.println("1. Bank Transfer Payment Service");
        System.out.println("2. Credit Card Payment Service");
        System.out.println("3. UPI Payment Service");

        Scanner in=new Scanner(System.in);
        int option=in.nextInt();
        if (option==1) {
            this.paymentService=new BankTransferPaymentService();
        } else if (option==2) {
            this.paymentService=new CreditCardPaymentService();
        }
        else if (option==3) {
            this.paymentService=new UPIPaymentService();
        }

        paymentService.processPayment();
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (Flight flight : flights) {
            total += flight.getTicketPrice();
        }
        return total;
    }

    @Override
    public void run() {
        // The code inside the run method will be executed by each thread
        // For example, each thread could represent a different passenger trying to book a flight
        Passenger passenger = new Passenger("Ganesha", "abc@gmail.com", "1234567890", true);
        Flight dflight = new DomesticFlight("DF185", "New York", 100, new Date(2022, 12, 24, 8,
                0), new Date(2022, 12, 24, 10, 0), 120);
        //Payment payment = new Payment("5252052525156252", 2500.623, "VISA");
        bookFlight(passenger, dflight);
    }


}
