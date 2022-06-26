package estructuraDatos;

import java.util.Objects;

public class Recurso implements Hashable{
    private int codigo;

    public Recurso(int valor){
        this.codigo = valor;
    }
    @Override
    public int hash(int tablesize) {
        return this.codigo%tablesize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recurso recurso = (Recurso) o;
        return this.codigo == recurso.codigo;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "codigo=" + this.codigo +
                '}';
    }

    public int getCodigo() {
        return this.codigo;
    }
}
