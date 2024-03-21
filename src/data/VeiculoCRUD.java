package data;

import model.Veiculo;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class VeiculoCRUD implements InterfaceVeiculoCRUD{
    private Map<String, Veiculo> veiculos;
    private final String arquivo = "veiculos.txt";
    public VeiculoCRUD() {
        veiculos = new HashMap<>();
        carregarDados();
    }

    public Map listarVeiculos() {
        return veiculos;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.put(veiculo.getRenavam(), veiculo);
        salvarDados();
    }

    public Veiculo buscarVeiculo(String renavam) {
        return veiculos.get(renavam);
    }
    public Map<String, Veiculo> getVeiculos() {
        return veiculos;
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        if (veiculos.containsKey(veiculo.getRenavam())) {
            veiculos.put(veiculo.getRenavam(), veiculo);
            salvarDados();
        } else {
            System.out.println("Veículo não encontrado para atualização.");
        }
    }

    public void removerVeiculo(String renavam) {
        veiculos.remove(renavam);
        salvarDados();
    }

    private void carregarDados() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivo))) {
            veiculos = (Map<String, Veiculo>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado. Criando novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            outputStream.writeObject(veiculos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            VeiculoCRUD veiculoCRUD = new VeiculoCRUD();
            InterfaceVeiculoCRUD RefServer = (InterfaceVeiculoCRUD) UnicastRemoteObject
                    .exportObject(veiculoCRUD, 6001);
            Registry registro = LocateRegistry.createRegistry(6001);
            registro.bind("VeiculoCRUD", RefServer);
        }catch (Exception e) {
            System.err.println("Servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
