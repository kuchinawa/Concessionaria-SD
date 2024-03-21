package teste3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try{
            Registry registro = LocateRegistry
                    .getRegistry("localhost", 20003);
            CalculadoraSimples stub =
                    (CalculadoraSimples) registro
                            .lookup("CalculadoraSimples");
            int resposta = stub.soma(new Operandos(10, 20));
            System.out.println("Resposta: " + resposta);
        }catch (Exception e){
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}

