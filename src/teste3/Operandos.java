package teste3;

import java.io.Serializable;

public class Operandos implements Serializable {
    private static final long serialVersionUID = 1L;
    public int n1;
    public int n2;

    public Operandos(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

}
