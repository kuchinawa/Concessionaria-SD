package teste;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements Hello {

    public Servidor() {}

    public String oi() {
        return "Oi, RMI!";
    }

    public static void main(String args[]) {
        try {
            Servidor refObjetoRemoto = new Servidor();
            Hello skeleton = (Hello) UnicastRemoteObject
                    .exportObject(refObjetoRemoto, Registry.REGISTRY_PORT);
            LocateRegistry.createRegistry( Registry.REGISTRY_PORT );
            Registry registro = LocateRegistry.getRegistry();

            registro.bind("Hello", skeleton);
            System.out.println("Servidor pronto na porta: " + Registry.REGISTRY_PORT + "!");

        } catch (Exception e) {
            System.err.println("Servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}