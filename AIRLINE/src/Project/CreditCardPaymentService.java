package Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CreditCardPaymentService implements PaymentService {
    private int sotp=(int)(Math.random()*1000);
    @Override
    public void processPayment() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter CARDHOLDER name: ");
        String enteredName = in.nextLine();

        System.out.print("Enter EXPIRY DATE: ");
        String enteredNo = in.nextLine();

        System.out.print("Enter CVV: ");
        String enteredCVV = in.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\DESKTOP\\IdeaProjects\\AIRLINE\\src\\Project\\creditcard.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String bankName = parts[0];
                String upiId = parts[1];
                String pin = parts[2];

                if (bankName.equals(enteredName) && upiId.equals(enteredNo) && pin.equals(enteredCVV)) {
                    System.out.println("Details are correct" );

                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while(true){
            System.out.println("ENTER OTP Sent To Your Mobile: ");
            System.out.println("Your otp is "+sotp+" Do not share with anyone");
            int otp=in.nextInt();
            if(otp==sotp){
                System.out.println("UPI payment received....... ");
                System.out.println("TICKET WILL BE SENT TO YOUR MAIL ID ");
                System.out.println("THANKS FOR BEING A VALUABLE CUSTOMER :) ");
                break;
            }
            else{
                System.out.println("WRONG OTP !!!  PLEASE RECHECK AND ENTER THE CORRECT ONE...");
            }
        }

    }


    }
