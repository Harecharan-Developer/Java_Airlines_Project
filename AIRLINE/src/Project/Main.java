package Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.time.*;

public class Main {
    static Airline airline = new Airline();
    static Flight[] Domesticflight = new DomesticFlight[100];//Upcasting
    static Flight[] Internationalflight = new InternationalFlight[100];//Upcasting
    static Flight[] BusinessClassflight = new BusinessClassFlight[100];//Upcasting
    static login user=new login();
    static Passenger[] Dpassenger = new Passenger[100];
    static Passenger[] Ipassenger= new Passenger[100];
    static Passenger[] Fpassenger = new Passenger[100];

    public static void main(String[] args) throws InterruptedException {
        int exit=1;
        while(exit ==1) {
            System.out.println("1: Admin");
            System.out.println("2: Passenger");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {

                user.details();
                admin();
            }
            else if(choice == 2) {
                try {
                    showtermsandconditions();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                login user=new login();
                user.details();
                passenger();
            }
            System.out.println("Do you want to Continue to Logout Yes(Press 1) OR To Force Exit(Press Any Integer)");
            exit=scanner.nextInt();
        }
    }
    public static  void showtermsandconditions() throws FileNotFoundException {
        // The name of the file to open
        String fileName = "C:\\DESKTOP\\IdeaProjects\\AIRLINE\\src\\Project\\terms_and_conditions.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Always close files
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
    private static void passenger() throws InterruptedException {
        int stop = 1;
        int option;
        Scanner in = new Scanner(System.in);
        while (stop == 1) {
            System.out.println("1: Search Flight");
            System.out.println("2: Book Flight");
            System.out.println("Enter your Choice : ");
            option = in.nextInt();
            in.nextLine();
            if (option == 1) {
                searchflights(airline);
            } else if (option == 2) {
                bookflight(airline);
            }
            System.out.println("Do you want to Continue to Passenger Menu Yes(Press 1) OR To Enter Main Menu (Press Any Integer)");
            stop=in.nextInt();
        }

    }

    private static void bookflight(Airline airline) throws InterruptedException {
        System.out.println("1: Domestic Flight");
        System.out.println("2: International Economic Class Flight");
        System.out.println("3: International First Class Flight");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option==1){
            Thread thread1 = new Thread(() -> {
                int flightid = -1;
                System.out.println("Enter Number of Passenger");
                int n=scanner.nextInt();
                scanner.nextLine();
                String Name = null;
                String Email = null;
                String Phone = null;
                Boolean Member = null;

                for(int i=0; i<n;i++) {
                    System.out.println("Enter Passenger Name: ");
                    Name = scanner.nextLine();
                    System.out.println("Enter Passenger Email: ");
                    Email = scanner.nextLine();
                    System.out.println("Enter Passenger Phone Number: ");
                    Phone = scanner.nextLine();
                    System.out.println("If you are member enter true or false");
                    Member = scanner.nextBoolean();//true or false
                    scanner.nextLine();
                    Dpassenger[i] = new Passenger(Name, Email, Phone, Member);
                }
                System.out.println("Enter the required Destination");
                String Destination = scanner.nextLine();
                airline.searchFlights_destination(Destination);

                for(int i=0; i<n;i++){


                    while (flightid == -1) {
                        System.out.println("Enter the FLight Number of the Flight you would like to travel");
                        String Flightnumber = scanner.nextLine();
                        for (int j = 0; j < Domesticflight[0].getTotalflight(); j++) {
                            if (Domesticflight[j].getFlightNumber().equals(Flightnumber)) {
                                flightid = j;
                            }
                        }
                        if (flightid != -1) {
                            Dpassenger[i] = new Passenger(Name, Email, Phone, Member);
                            airline.bookFlight(Dpassenger[i], Domesticflight[flightid]);


                        } else {
                            System.out.println("Enter the correct flight number");
                        }
                    }


                }
                if(flightid != -1) {
                    airline.Payment(Domesticflight[flightid]);
                }

            });
            thread1.start();
            thread1.join();//To wait until the thread terminates
        }
        else if (option==2){
            Thread thread1 = new Thread(() -> {
                int flightid = -1;
                System.out.println("Enter Number of Passenger");
                int n=scanner.nextInt();
                scanner.nextLine();
                String Name = null;
                String Email = null;
                String Phone = null;
                Boolean Member = null;

                for(int i=0; i<n;i++) {
                    System.out.println("Enter Passenger Name: ");
                    Name = scanner.nextLine();
                    System.out.println("Enter Passenger Email: ");
                    Email = scanner.nextLine();
                    System.out.println("Enter Passenger Phone Number: ");
                    Phone = scanner.nextLine();
                    System.out.println("If you are member enter true or false");
                    Member = scanner.nextBoolean();//true or false
                    scanner.nextLine();
                    Ipassenger[i] = new Passenger(Name, Email, Phone, Member);
                }
                System.out.println("Enter the required Destination");
                String Destination = scanner.nextLine();
                airline.searchFlights_destination(Destination);
                for(int i=0; i<n;i++){


                    while (flightid == -1) {
                        System.out.println("Enter the FLight Number of the Flight you would like to travel");
                        String Flightnumber = scanner.nextLine();
                        for (int j = 0; j < Internationalflight[0].getTotalflight(); j++) {
                            if (Internationalflight[j].getFlightNumber().equals(Flightnumber)) {
                                flightid = j;
                            }
                        }
                        if (flightid != -1) {
                            Ipassenger[i] = new Passenger(Name, Email, Phone, Member);
                            airline.bookFlight(Ipassenger[i], Internationalflight[flightid]);


                        } else {
                            System.out.println("Enter the correct flight number");
                        }
                    }


                }
                if(flightid != -1) {
                    airline.Payment(Internationalflight[flightid]);
                }

            });
            thread1.start();
            thread1.join();//To wait until the thread terminates
        }
        else if(option==3){
            Thread thread1 = new Thread(() -> {
                int flightid = -1;
                System.out.println("Enter Number of Passenger");
                int n=scanner.nextInt();
                scanner.nextLine();
                String Name = null;
                String Email = null;
                String Phone = null;
                Boolean Member = null;

                for(int i=0; i<n;i++) {
                    System.out.println("Enter Passenger Name: ");
                    Name = scanner.nextLine();
                    System.out.println("Enter Passenger Email: ");
                    Email = scanner.nextLine();
                    System.out.println("Enter Passenger Phone Number: ");
                    Phone = scanner.nextLine();
                    System.out.println("If you are member enter true or false");
                    Member = scanner.nextBoolean();//true or false
                    scanner.nextLine();
                    Fpassenger[i] = new Passenger(Name, Email, Phone, Member);
                }
                System.out.println("Enter the required Destination");
                String Destination = scanner.nextLine();
                airline.searchFlights_destination(Destination);

                for(int i=0; i<n;i++){


                    while (flightid == -1) {
                        System.out.println("Enter the FLight Number of the Flight you would like to travel");
                        String Flightnumber = scanner.nextLine();
                        for (int j = 0; j < BusinessClassflight[0].getTotalflight(); j++) {
                            if (BusinessClassflight[j].getFlightNumber().equals(Flightnumber)) {
                                flightid = j;
                            }
                        }
                        if (flightid != -1) {
                            Fpassenger[i] = new Passenger(Name, Email, Phone, Member);
                            airline.bookFlight(Fpassenger[i], BusinessClassflight[flightid]);


                        } else {
                            System.out.println("Enter the correct flight number");
                        }
                    }


                }
                if(flightid != -1) {
                    airline.Payment(BusinessClassflight[flightid]);
                }

            });
            thread1.start();
            thread1.join();//To wait until the thread terminates
        }


    }
//        Airline airline = new Airline();
////        long millis = System.currentTimeMillis();
////        java.sql.Date date = new java.sql.Date(millis);
////        LocalDate dt = LocalDate.parse(date.toString());
//        Flight domesticFlight1 = new DomesticFlight("DF123", "New York", 100, new Date(2022, 12, 24, 8,
//                0), new Date(2022, 12, 24, 10, 0), 120);
//        Flight domesticFlight2 = new DomesticFlight("DF456", "Chicago", 75, new Date(2022, 12, 24, 12,
//                0), new Date(2022, 12, 24, 14, 0), 120);
//        Flight internationalFlight = new InternationalFlight("IF789", "Paris", 50, new Date(2022, 12,
//                24, 16, 0), new Date(2022, 12, 25, 8, 0), 360);
//        airline.addFlight(domesticFlight1);
//        airline.addFlight(domesticFlight2);
//        airline.addFlight(internationalFlight);
//        Thread thread1 = new Thread(() -> {
//            Passenger passenger1 = new Passenger("John Smith", "john.smith@gmail.com", "123-456-7890",
//                    false);
//
//            airline.bookFlight(passenger1, domesticFlight1);
//            //airline.bookFlight(passenger1, domesticFlight2, payment1);
//        });
//        thread1.start();
//        Thread thread2 = new Thread(() -> {
//            Passenger passenger2 = new Passenger("Jane Doe", "jane.doe@gmail.com", "987-654-3210",
//                    true);
//
//            //airline.bookFlight(passenger2, internationalFlight, payment2);
//        });
//        thread2.start();
//    }}

    public static void admin() throws InterruptedException {

        int stop = 1;
        int option;
        Scanner in = new Scanner(System.in);
        while (stop == 1) {
            System.out.println("1: Add a new Domestic Class Flight");
            System.out.println("2: Add a new International Economic Class Flight");
            System.out.println("3: Add a new First Class and Business Class Flight");
            System.out.println("4: Get the Total Revenue");
            System.out.println("5: Get the details of flight - Search Flights");
            System.out.println("Enter your Choice : ");
            option = in.nextInt();
            if (option == 1) {
                domestic(airline);
            } else if (option == 2) {
                international(airline);
            }
            else if (option == 3) {
                business(airline);
            }
            else if (option ==4){
                System.out.println(airline.getTotalRevenue());
            }
            else if (option == 5){
                searchflights(airline);

            }
            System.out.println("Do you want to Continue to Admin Menu Yes(Press 1) OR To Enter Main Menu (Press Any Integer)");
            stop=in.nextInt();
        }
    }

    private static void searchflights(Airline airline) throws InterruptedException{
        int stop = 1;
        Scanner in = new Scanner(System.in);
        while(stop==1) {
            System.out.println("1: Search using Destination");
            System.out.println("2: Search using Flight Number");
            System.out.println("3: Search using Min Seats");
            int option;
            Scanner scan = new Scanner(System.in);
            option = scan.nextInt();
            scan.nextLine();
            if (option == 1) {
                System.out.println("Enter the required Destination");
                String destination = scan.nextLine();
                airline.searchFlights_destination(destination);
            } else if (option == 2) {
                System.out.println("Enter the required Flight Number");
                String flightNumber = scan.nextLine();
                airline.searchFlights_number(flightNumber);
            } else if (option == 3) {
                System.out.println("Enter the Minimum Number of Seats Required");
                int minseats = scan.nextInt();
                airline.searchFlights_minseats(minseats);
            }
            System.out.println("Do you want to Continue to Search Menu Yes(Press 1) OR To Enter Menu (Press Any Integer)");
            stop=scan.nextInt();
        }
    }


    public static void domestic(Airline airline) throws InterruptedException {

        System.out.println("Enter Number of Flights to Add : ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();//To avoid error after using nextInt along with nextLine
        String FlightNumber;
        String Destination;
        int Seats;
        int Hours;
        int Minutes;
        int Duration;
        int DHours;
        int Dminute;
        int sumHours;
        int arrMinutes;
        int arrHours;

        for (int i = 0; i < n; i++) {

            System.out.println("Enter Flight Number : ");
            FlightNumber = scan.nextLine();
            System.out.println("Enter Destination : ");
            Destination = scan.nextLine();
            System.out.println("Enter Number of Seats : ");
            Seats = scan.nextInt();
            //For getting today's Date
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            LocalDate dt = LocalDate.parse(date.toString());
            System.out.println("Enter Departure Time in hh mm (hours space minutes) format: ");
            Hours = scan.nextInt();
            Minutes = scan.nextInt();
            System.out.println("Enter Total Duration in hh mm format: ");
            DHours = scan.nextInt();
            Dminute = scan.nextInt();
            Duration = (DHours * 60) + Dminute;
            scan.nextLine();
            int change = 0;
            //First we are adding minutes to current time and then we add hours
            if ((Dminute + Minutes) > 60) {
                sumHours = DHours + Hours + 1;//Adding one hour to total hours and reducing 60 minutes from sum of minutes
                arrMinutes = Dminute + Minutes - 60;//Converting to minutes which are left after converting 60minutes from it to hours
                if (sumHours > 24) {
                    arrHours = sumHours - 24;//Converting to next day hours
                    change = 1;

                } else {
                    arrHours = sumHours;

                }
            } else {
                arrHours = DHours + Hours;
                arrMinutes = Dminute + Minutes;
                if (arrHours > 24) {
                    arrHours = arrHours - 24;//Converting to next day hours
                    change = 1;
                }
            }
            if (change == 1) {
                long milli = System.currentTimeMillis();
                java.sql.Date tdate = new java.sql.Date(milli + (1000 * 60 * 60 * 24));
                LocalDate at = LocalDate.parse(tdate.toString());

                Domesticflight[i] = new DomesticFlight(FlightNumber, Destination, Seats, new Date(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), Hours, Minutes), new Date(at.getYear(), at.getMonthValue(), at.getDayOfMonth(), arrHours, arrMinutes), Duration);
                airline.addFlight(Domesticflight[i]);
            } else {
                long milli = System.currentTimeMillis();
                java.sql.Date tdate = new java.sql.Date(milli + (1000 * 60 * 60 * 24));
                LocalDate at = LocalDate.parse(tdate.toString());


                Domesticflight[i] = new DomesticFlight(FlightNumber, Destination, Seats, new Date(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), Hours, Minutes), new Date(at.getYear(), at.getMonthValue(), at.getDayOfMonth(), arrHours, arrMinutes), Duration);
                airline.addFlight(Domesticflight[i]);

            }
        }

    }

    private static void international(Airline airline) throws InterruptedException {

        System.out.println("Enter Number of Flights to Add : ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();//To avoid error after using nextInt along with nextLine
        String FlightNumber;
        String Destination;
        int Seats;
        int Hours;
        int Minutes;
        int Duration;
        int DHours;
        int Dminute;
        int sumHours;
        int arrMinutes;
        int arrHours;

        for (int i = 0; i < n; i++) {

            System.out.println("Enter Flight Number : ");
            FlightNumber = scan.nextLine();
            System.out.println("Enter Destination : ");
            Destination = scan.nextLine();
            System.out.println("Enter Number of Seats : ");
            Seats = scan.nextInt();
            //For getting today's Date
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            LocalDate dt = LocalDate.parse(date.toString());
            System.out.println("Enter Departure Time in hh mm (hours space minutes) format: ");
            Hours = scan.nextInt();
            Minutes = scan.nextInt();
            System.out.println("Enter Total Duration in hh mm format: ");
            DHours = scan.nextInt();
            Dminute = scan.nextInt();
            Duration = (DHours * 60) + Dminute;
            scan.nextLine();
            int change = 0;
            //First we are adding minutes to current time and then we add hours
            if ((Dminute + Minutes) > 60) {
                sumHours = DHours + Hours + 1;//Adding one hour to total hours and reducing 60 minutes from sum of minutes
                arrMinutes = Dminute + Minutes - 60;//Converting to minutes which are left after converting 60minutes from it to hours
                if (sumHours > 24) {
                    arrHours = sumHours - 24;//Converting to next day hours
                    change = 1;

                } else {
                    arrHours = sumHours;

                }
            } else {
                arrHours = DHours + Hours;
                arrMinutes = Dminute + Minutes;
                if (arrHours > 24) {
                    arrHours = arrHours - 24;//Converting to next day hours
                    change = 1;
                }
            }
            if (change == 1) {
                long milli = System.currentTimeMillis();
                java.sql.Date tdate = new java.sql.Date(milli + (1000 * 60 * 60 * 24));
                LocalDate at = LocalDate.parse(tdate.toString());

                Internationalflight[i] = new InternationalFlight(FlightNumber, Destination, Seats, new Date(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), Hours, Minutes), new Date(at.getYear(), at.getMonthValue(), at.getDayOfMonth(), arrHours, arrMinutes), Duration);
                airline.addFlight(Internationalflight[i]);
            } else {
                long milli = System.currentTimeMillis();
                java.sql.Date tdate = new java.sql.Date(milli + (1000 * 60 * 60 * 24));
                LocalDate at = LocalDate.parse(tdate.toString());


                Internationalflight[i] = new InternationalFlight(FlightNumber, Destination, Seats, new Date(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), Hours, Minutes), new Date(at.getYear(), at.getMonthValue(), at.getDayOfMonth(), arrHours, arrMinutes), Duration);
                airline.addFlight(Internationalflight[i]);

            }
        }

    }

    private static void business(Airline airline) throws InterruptedException{

        System.out.println("Enter Number of Flights to Add : ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();//To avoid error after using nextInt along with nextLine
        String FlightNumber;
        String Destination;
        int Seats;
        int Hours;
        int Minutes;
        int Duration;
        int DHours;
        int Dminute;
        int sumHours;
        int arrMinutes;
        int arrHours;

        for (int i = 0; i < n; i++) {

            System.out.println("Enter Flight Number : ");
            FlightNumber = scan.nextLine();
            System.out.println("Enter Destination : ");
            Destination = scan.nextLine();
            System.out.println("Enter Number of Seats : ");
            Seats = scan.nextInt();
            //For getting today's Date
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            LocalDate dt = LocalDate.parse(date.toString());
            System.out.println("Enter Departure Time in hh mm (hours space minutes) format: ");
            Hours = scan.nextInt();
            Minutes = scan.nextInt();
            System.out.println("Enter Total Duration in hh mm format: ");
            DHours = scan.nextInt();
            Dminute = scan.nextInt();
            Duration = (DHours * 60) + Dminute;
            scan.nextLine();
            int change = 0;
            //First we are adding minutes to current time and then we add hours
            if ((Dminute + Minutes) > 60) {
                sumHours = DHours + Hours + 1;//Adding one hour to total hours and reducing 60 minutes from sum of minutes
                arrMinutes = Dminute + Minutes - 60;//Converting to minutes which are left after converting 60minutes from it to hours
                if (sumHours > 24) {
                    arrHours = sumHours - 24;//Converting to next day hours
                    change = 1;

                } else {
                    arrHours = sumHours;

                }
            } else {
                arrHours = DHours + Hours;
                arrMinutes = Dminute + Minutes;
                if (arrHours > 24) {
                    arrHours = arrHours - 24;//Converting to next day hours
                    change = 1;
                }
            }
            if (change == 1) {
                long milli = System.currentTimeMillis();
                java.sql.Date tdate = new java.sql.Date(milli + (1000 * 60 * 60 * 24));
                LocalDate at = LocalDate.parse(tdate.toString());

                BusinessClassflight[i] = new BusinessClassFlight(FlightNumber, Destination, Seats, new Date(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), Hours, Minutes), new Date(at.getYear(), at.getMonthValue(), at.getDayOfMonth(), arrHours, arrMinutes), Duration);
                airline.addFlight(BusinessClassflight[i]);
            } else {
                long milli = System.currentTimeMillis();
                java.sql.Date tdate = new java.sql.Date(milli + (1000 * 60 * 60 * 24));
                LocalDate at = LocalDate.parse(tdate.toString());


                BusinessClassflight[i] = new BusinessClassFlight(FlightNumber, Destination, Seats, new Date(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), Hours, Minutes), new Date(at.getYear(), at.getMonthValue(), at.getDayOfMonth(), arrHours, arrMinutes), Duration);
                airline.addFlight(BusinessClassflight[i]);

            }
        }

    }
}


