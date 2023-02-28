import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MessengerServiceImpl implements MessengerService {

  @Override
  public String sendMessage(String clientMessage) {
    return "Client Message".equals(clientMessage) ? "Server Message" : null;
  }

  public String unexposedMethod() {
    return null;
    /* code */ }

  public static void main(String[] args) throws Exception {
    MessengerService server = new MessengerServiceImpl();
    MessengerService stub = (MessengerService) UnicastRemoteObject
        .exportObject((MessengerService) server, 0);
    Registry registry = LocateRegistry.createRegistry(1099);

    registry.rebind("MessengerService", stub);
  }
}
