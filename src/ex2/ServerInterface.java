package ex2;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
  int sendMessage(String clientMessage) throws RemoteException;
}