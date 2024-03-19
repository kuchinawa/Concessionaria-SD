package teste;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    // método a ser implementado
    String oi() throws RemoteException;
}