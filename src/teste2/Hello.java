package teste2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String oi() throws RemoteException;
}