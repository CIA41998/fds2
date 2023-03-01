package ex2;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) throws Exception {
    // Find server
    Registry registry = LocateRegistry.getRegistry();
    ServerInterface server = (ServerInterface) registry
        .lookup("MessengerService");

    // Read terminal input
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Please input a string to count the letters");
    String str = keyboard.nextLine();

    // Send message to server
    int responseMessage = server.sendMessage(str);

    // Print response
    System.out.print(responseMessage + " characters");
  }
}
