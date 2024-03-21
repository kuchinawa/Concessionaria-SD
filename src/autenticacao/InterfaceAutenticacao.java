package autenticacao;

import model.Usuario;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceAutenticacao extends Remote {
    public boolean autenticar(String login, String senha) throws RemoteException;
    public void adicionarUsuario(Usuario usuario) throws RemoteException;
}
