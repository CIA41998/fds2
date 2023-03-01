package ex3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) throws Exception {
    // Keep trying to connect to server until it is available
    // or try again if message is not sent
    String inputString = "";
    while (true) {
      try {
        // Find server
        Registry registry = LocateRegistry.getRegistry();
        ServerInterface server = (ServerInterface) registry
            .lookup("MessengerService");

        // Read terminal input
        // Only ask for input if it is empty, otherwise use previous input
        if (inputString.equals("")) {
          Scanner keyboard = new Scanner(System.in);
          System.out.println("Please input a string to count the letters");
          inputString = keyboard.nextLine();
        }

        // Send message to server
        int responseMessage = server.sendMessage(inputString);

        // Print response
        System.out.print(responseMessage + " characters");
        break;
      } catch (Exception e) {
        // if java.rmi.ConnectException
        if (e.getCause().getClass().getName().equals("java.net.ConnectException")) {
          System.out.println("Server unavailable, trying again in 1 second");
          Thread.sleep(1000);
        } else {
          System.out.println("Message not sent, trying again in 1 second");
          Thread.sleep(1000);
        }
      }
    }
  }
}
