package teste2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 20002);
            Hello stub = (Hello) registro.lookup("Hello");
            String resposta = stub.oi();
            System.out.println("Resposta: " + resposta);
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}