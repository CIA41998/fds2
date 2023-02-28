import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessengerClient {
  public static void main(String[] args) throws Exception {
    Registry registry = LocateRegistry.getRegistry();
    MessengerService server = (MessengerService) registry
        .lookup("MessengerService");
    String responseMessage = server.sendMessage("Client Message");
    String expectedMessage = "Server Message";

    assertEquals(expectedMessage, responseMessage);
  }

  private static void assertEquals(String expectedMessage, String responseMessage) {
    if (expectedMessage.equals(responseMessage)) {
      System.out.println("Test passed");
    } else {
      System.out.println("Test failed");
    }
  }
}
