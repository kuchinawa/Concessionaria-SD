package autenticacao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceAutenticacao extends Remote {
    public void adicionarUsuario(String login, String senha, String nome, String email, boolean funcionario) throws RemoteException;
    public int autenticar(String login, String senha) throws RemoteException;
}
