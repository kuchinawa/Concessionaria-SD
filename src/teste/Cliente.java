package teste;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    private Cliente() {}
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            Hello stubObjRemotoCliente = (Hello) registro.lookup("Hello");
            String resposta = stubObjRemotoCliente.oi();
            System.out.println("Chamando método oi(): " + resposta);
        } catch (Exception e) {
            System.err.println("Cliente: " + e.toString());
            e.printStackTrace();

        }
    }
}