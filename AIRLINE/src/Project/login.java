package Project;
import java.io.*;
import java.util.*;
import java.util.Random;
 class login {
    Scanner sc = new Scanner(System.in);
    String uname;
    int password=123;
//    int password=(int)(Math.random()*1000);
    String response;
    public void details(){
        System.out.println("--------------WELCOME TO AIRLINE RESERVATION SYSTEM--------------");
        System.out.println("");
        System.out.println("---------------------------LOGIN SYSTEM--------------------------");
        System.out.println("ENTER YOUR USER NAME:");
        uname=sc.next();
        System.out.println("Hey "+uname+" are you a new user? Yes or No");
        response=sc.next();
        if(response.equals("Yes")){
            register();
        }
        else{
            signin();
        }
    }
     public void register(){
         System.out.println("---------------------------REGISTER SYSTEM--------------------------");
         System.out.println("");
         System.out.println("Enter your new pin:");
         password=sc.nextInt();
         System.out.println("");
         System.out.println("Registering "+uname+" with pin "+password+"...");
         System.out.println("Congratulations!!!You have successfully created an account");
         try {
             BufferedWriter writer = new BufferedWriter(new FileWriter("login.txt",true));
             writer.write(uname + "," + password);
             writer.close();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
         signin();
     }

     public void signin(){
         Scanner sc_in = new Scanner(System.in);
         boolean successfulSignIn = false;
         while (!successfulSignIn) {
             System.out.print("Enter username: ");
             String enteredUsername = sc_in.nextLine();

             System.out.print("Enter password: ");
             String enteredPassword = sc_in.nextLine();

             try {
                 BufferedReader reader = new BufferedReader(new FileReader("C:\\DESKTOP\\IdeaProjects\\AIRLINE\\src\\Project\\login.txt"));
                 String line;
                 while ((line = reader.readLine()) != null) {
                     String[] parts = line.split(",");
                     String username = parts[0];
                     String password = parts[1];
                     if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
                         System.out.println("Sign in successful!");
                         successfulSignIn = true;
                         break;
                     }
                 }
                 reader.close();

             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }
     }
 }