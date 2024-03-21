package autenticacao;

import model.Usuario;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ServidorAutenticacao {
    private Map<String, Usuario> usuarios;
    private final String arquivo = "usuarios.txt";



    public ServidorAutenticacao() {
        usuarios = new HashMap<>();
        carregarDados();
    }

    public boolean autenticar(String login, String senha) {
        Usuario usuario = usuarios.get(login);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getLogin(), usuario);
        salvarDados();
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
}
