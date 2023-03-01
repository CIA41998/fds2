package ex2;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerInterface {

  @Override
  public int sendMessage(String clientMessage) {
    // Print message
    System.out.println(clientMessage);

    // Return number of letters
    return printAndCountLetters(clientMessage);
  }

  public String unexposedMethod() {
    return null;
    /* code */ }

  public static void main(String[] args) throws Exception {
    // Start server
    ServerInterface server = new Server();
    ServerInterface stub = (ServerInterface) UnicastRemoteObject
        .exportObject((ServerInterface) server, 0);
    Registry registry = LocateRegistry.createRegistry(1099);

    registry.rebind("MessengerService", stub);
  }

  public static int printAndCountLetters(String word) {
    int numberLetters = 0;
    for (int i = 0; i < word.length(); i++) {
      if (Character.isLetter(word.charAt(i)))
        numberLetters++;
    }
    return numberLetters;
  }
}
