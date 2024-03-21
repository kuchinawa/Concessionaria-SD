package ui;

import gateway.InterfaceGateway;
import model.Veiculo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {
    static InterfaceGateway stubGateway;
    public static void main(String[] args) throws RemoteException {

        try{
            Registry registro = LocateRegistry.getRegistry("localhost", 5002);
            stubGateway = (InterfaceGateway) registro.lookup("Gateway");
        }catch (Exception e){

        }


        Scanner scanner = new Scanner(System.in);
        boolean parar = false;

        while (!parar) {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Autenticação");
            System.out.println("2 - Adicionar carro");
            System.out.println("3 - Apagar carro");
            System.out.println("4 - Listar carros");
            System.out.println("5 - Pesquisar carro");
            System.out.println("6 - Alterar atributos de carros");
            System.out.println("7 - Atualizar listagem de carros enviada aos clientes conectados");
            System.out.println("8 - Exibir quantidade de carros");
            System.out.println("9 - Comprar carro");
            System.out.println("10 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    autenticacao();
                    break;
                case 2:
                    adicionarCarro(stubGateway, scanner);
                    break;
                case 3:
                    apagarCarro(stubGateway, scanner);
                    break;
                case 4:
                    listarCarros(stubGateway);
                    break;
                case 5:
                    pesquisarCarro(stubGateway, scanner);
                    break;
                case 6:
                    alterarAtributosCarros(stubGateway, scanner);
                    break;
                case 7:
                    atualizarListagemCarros();
                    break;
                case 8:
                    exibirQuantidadeCarros(stubGateway);
                    break;
                case 9:
                    comprarCarro();
                    break;
                case 10:
                    parar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Fim do programa.");
    }


    private static void autenticacao() {
    }

    private static void adicionarCarro(InterfaceGateway stubGateway, Scanner scanner) throws RemoteException {
        System.out.println("Adicionar carro:");
        System.out.print("Renavam: ");
        String renavam = scanner.next();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Escolha a categoria do carro:");
        System.out.println("1 - Econômico");
        System.out.println("2 - Intermediário");
        System.out.println("3 - Executivo");

        int opcaoCategoria = scanner.nextInt();


        String categoria;
        switch (opcaoCategoria) {
            case 1:
                categoria = "Econômico";
                break;
            case 2:
                categoria = "Intermediário";
                break;
            case 3:
                categoria = "Executivo";
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.print("Ano de fabricação: ");
        int anoFabricacao = scanner.nextInt();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        Veiculo veiculo = new Veiculo(renavam, nome, categoria, anoFabricacao, preco);
        stubGateway.adicionarVeiculo(veiculo);
        System.out.println("Carro adicionado com sucesso!");
    }

    private static void apagarCarro(InterfaceGateway stubGateway, Scanner scanner) throws RemoteException {
        System.out.println("Apagar carro:");
        System.out.print("Informe o renavam do carro que deseja remover: ");
        String valor = scanner.next();

        if (valor.matches("\\d+")) {
            Veiculo veiculo = stubGateway.buscarVeiculo(valor);
            if (veiculo != null) {
                stubGateway.removerVeiculo(valor);
                System.out.println("Carro removido com sucesso!");
            } else {
                System.out.println("Carro não encontrado.");
            }
        } else {
            System.out.println("por enquanto só da pra remover pelo renavam, não pelo nome.");
        }
    }

    private static void listarCarros(InterfaceGateway stubGateway) throws RemoteException{
        stubGateway.listarVeiculos();
    }


    private static void pesquisarCarro(InterfaceGateway stubGateway, Scanner scanner) throws RemoteException {
        System.out.println("Pesquisar carro:");
        System.out.print("Informe o nome ou renavam do carro: ");
        String valor = scanner.next();

        Veiculo veiculo = stubGateway.buscarVeiculo(valor);
        if (veiculo != null) {
            System.out.println("Carro encontrado: " + veiculo);
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    private static void alterarAtributosCarros(InterfaceGateway stubGateway, Scanner scanner) throws RemoteException {
        System.out.println("Alterar atributos de carros:");
        System.out.print("Informe o renavam do carro que deseja atualizar: ");
        String renavam = scanner.next();
        Veiculo veiculo = stubGateway.buscarVeiculo(renavam);
        if (veiculo != null) {
            System.out.println("Informe os novos detalhes do carro:");
            System.out.print("Nome: ");
            String nome = scanner.next();
            System.out.print("Categoria: ");
            String categoria = scanner.next();
            System.out.print("Ano de fabricação: ");
            int anoFabricacao = scanner.nextInt();
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();

            veiculo.setNome(nome);
            veiculo.setCategoria(categoria);
            veiculo.setAnoFabricacao(anoFabricacao);
            veiculo.setPreco(preco);
            stubGateway.atualizarVeiculo(veiculo);
            System.out.println("Carro atualizado com sucesso!");
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    private static void atualizarListagemCarros() throws RemoteException{

    }

    private static void exibirQuantidadeCarros(InterfaceGateway stubGateway) throws RemoteException{
        //int quantidade = stubGateway.getVeiculos().size();
//System.out.println("Quantidade de carros: " + quantidade);
    }


    private static void comprarCarro() throws RemoteException{

    }
}