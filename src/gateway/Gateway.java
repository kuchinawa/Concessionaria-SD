package gateway;

import autenticacao.InterfaceAutenticacao;
import data.InterfaceVeiculoCRUD;
import model.Usuario;
import model.Veiculo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Gateway implements InterfaceGateway{

    static InterfaceAutenticacao stubIntAut;
    static InterfaceVeiculoCRUD stubVeiculoCRUD;

    public static void main(String[] args) {
        try {
            Registry registro1 = LocateRegistry.getRegistry("localhost", 5000);
            stubIntAut = (InterfaceAutenticacao) registro1.lookup("ServidorAutenticacao");

            Registry registro2 = LocateRegistry.getRegistry("localhost", 5001);
            stubVeiculoCRUD = (InterfaceVeiculoCRUD) registro2.lookup("VeiculoCRUD");

            Gateway refObjetoRemoto = new Gateway();
            InterfaceGateway RefServer = (InterfaceGateway) UnicastRemoteObject
                    .exportObject(refObjetoRemoto, 5002);
            Registry registro = LocateRegistry.createRegistry(5002);
            registro.bind("Gateway", RefServer);


        }catch (Exception e) {
            System.err.println("Servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public boolean autenticar(String login, String senha) throws RemoteException {
        boolean foi = stubIntAut.autenticar(login, senha);
        return foi;
    }

    @Override
    public void adicionarUsuario(Usuario usuario) throws RemoteException {
        stubIntAut.adicionarUsuario(usuario);
    }

    @Override
    public void adicionarVeiculo(Veiculo veiculo) throws RemoteException {
        stubVeiculoCRUD.adicionarVeiculo(veiculo);
    }

    @Override
    public void removerVeiculo(String placa) throws RemoteException {
        stubVeiculoCRUD.removerVeiculo(placa);
    }

    @Override
    public Veiculo buscarVeiculo(String placa) throws RemoteException {
        Veiculo veiculo = stubVeiculoCRUD.buscarVeiculo(placa);
        return veiculo;

    }

    @Override
    public void atualizarVeiculo(Veiculo veiculo) throws RemoteException {
        stubVeiculoCRUD.atualizarVeiculo(veiculo);
    }

    @Override
    public void listarVeiculos() throws RemoteException {
        stubVeiculoCRUD.listarVeiculos();
    }
}
