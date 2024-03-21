package teste3;

public class ImplCalculadoraSimples implements CalculadoraSimples{
    @Override
    public int soma(Operandos ops) {
        System.out.println("Somando " + ops.n1 + " + " + ops.n2);
        return ops.n1 + ops.n2;
    }
}
