package autenticacao;

import model.Usuario;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServidorAutenticacao implements InterfaceAutenticacao{
    private Map<String, Usuario> usuarios;
    private final String arquivo = "usuarios.txt";



    public ServidorAutenticacao() {
        usuarios = new HashMap<>();
        carregarDados();
    }

    @Override
    public void adicionarUsuario(String login, String senha, String nome, String email, boolean funcionario) throws RemoteException {
        Usuario usuario = new Usuario(login, senha, nome, email, funcionario);
        usuarios.put(login, usuario);
        salvarDados();
    }

    public int autenticar(String login, String senha) {
        Usuario usuario = usuarios.get(login);
        if(usuario != null && usuario.getSenha().equals(senha)){
            if(usuario.isFuncionario()){
                return 2; // funcionario
            } else {
                return 1; // cliente
            }
        } else {
            return 0; // não autenticado
        }
    }


    private void salvarDados() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            outputStream.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    private  void carregarDados() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivo))) {
            usuarios = (Map<String, Usuario>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado. Criando novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            ServidorAutenticacao servidor = new ServidorAutenticacao();
            InterfaceAutenticacao RefServer = (InterfaceAutenticacao) UnicastRemoteObject
                    .exportObject(servidor, 6000);
            Registry registro = LocateRegistry.createRegistry(6000);
            registro.bind("ServidorAutenticacao", RefServer);

        }catch (Exception e) {
            System.err.println("Servidor: " + e.toString());
            e.printStackTrace();
        }

    }
}
