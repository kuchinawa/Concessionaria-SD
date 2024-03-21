package teste2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    public static void main(String[] args) {
        try {
            ImplHello refObjetoRemoto = new ImplHello();
            Hello skeleton =
                    (Hello) UnicastRemoteObject
                            .exportObject(refObjetoRemoto, 0);
            LocateRegistry.createRegistry(20002);
            Registry registro =
                    LocateRegistry.getRegistry(20002);

            registro.bind("Hello", skeleton);
            System.out.println("Servidor pronto.");
        }catch (Exception e){
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
