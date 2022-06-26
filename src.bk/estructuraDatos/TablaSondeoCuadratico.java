package estructuraDatos;

import excepciones.HashTableFullException;

import java.rmi.server.ExportException;

public class TablaSondeoCuadratico extends ExploracionTablaHash{
    public TablaSondeoCuadratico(){
        super();
    }

    public TablaSondeoCuadratico(int tamanio){
        super(tamanio);
    }
    @Override
    protected int buscarPosicion(Hashable valor) {
        if (!this.estaVacio()) throw new HashTableFullException("No existen mas posiciones disponibles");
        int colision = 0;
        int posicionActual = valor.hash(vector.length);
        while (vector[posicionActual] != null && !vector[posicionActual].getElemento().equals(valor)){
            posicionActual += 2 * ++colision -1;
            if (posicionActual >= vector.length) {
                posicionActual -= vector.length;
            }
        }
        return posicionActual;
    }

    @Override
    public void imprimirTablaHash() {
        super.imprimirTablaHash();
        for (int i = 0; i < this.vector.length ; i++) {
            System.out.println(" | " + i + " | " + this.vector[i] + " |");
        }
    }

}
