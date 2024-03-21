package gateway;

import autenticacao.InterfaceAutenticacao;
import data.InterfaceVeiculoCRUD;
import model.Veiculo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class Gateway implements InterfaceGateway{

    static InterfaceAutenticacao stubIntAut;
    static InterfaceVeiculoCRUD stubVeiculoCRUD;

    public static void main(String[] args) {
        try {
            Registry registro1 = LocateRegistry.getRegistry("localhost", 6000);
            stubIntAut = (InterfaceAutenticacao) registro1.lookup("ServidorAutenticacao");

            Registry registro2 = LocateRegistry.getRegistry("localhost", 6001);
            stubVeiculoCRUD = (InterfaceVeiculoCRUD) registro2.lookup("VeiculoCRUD");

            Gateway refObjetoRemoto = new Gateway();
            InterfaceGateway RefServer = (InterfaceGateway) UnicastRemoteObject
                    .exportObject(refObjetoRemoto, 6002);
            Registry registro = LocateRegistry.createRegistry(6002);
            registro.bind("Gateway", RefServer);


        }catch (Exception e) {
            System.err.println("Servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public int autenticar(String login, String senha) throws RemoteException {
        int foi = stubIntAut.autenticar(login, senha);
        return foi;
    }

    @Override
    public void adicionarUsuario(String login, String senha, String nome, String email, boolean funcionario) throws RemoteException {
        stubIntAut.adicionarUsuario(login, senha, nome, email, funcionario);
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
    public Map listarVeiculos() throws RemoteException {
        Map lista = stubVeiculoCRUD.listarVeiculos();
        return lista;
    }
}
