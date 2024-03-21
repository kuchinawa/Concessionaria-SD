package teste3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraSimples extends Remote {
    int soma (Operandos ops) throws RemoteException;
}
