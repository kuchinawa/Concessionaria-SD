package teste3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    public static void main(String[] args) {
        try {
            ImplCalculadoraSimples refObjetorRemoto = new ImplCalculadoraSimples();
            CalculadoraSimples skeleton =
                    (CalculadoraSimples) UnicastRemoteObject
                            .exportObject(refObjetorRemoto, 0);
            LocateRegistry.createRegistry(20003);
            Registry registro =
                    LocateRegistry.getRegistry(20003);
            registro.bind("CalculadoraSimples", skeleton);
            System.out.println("Servidor pronto.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }

}
