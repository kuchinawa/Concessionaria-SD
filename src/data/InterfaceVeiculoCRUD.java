package data;

import model.Veiculo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface InterfaceVeiculoCRUD extends Remote {
    public void adicionarVeiculo(Veiculo veiculo) throws RemoteException;
    public void removerVeiculo(String placa) throws RemoteException;
    public Veiculo buscarVeiculo(String placa) throws RemoteException;
    public void atualizarVeiculo(Veiculo veiculo) throws RemoteException;
    public Map listarVeiculos() throws RemoteException;
}
