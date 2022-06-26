package estructuraDatos;

import excepciones.HashTableFullException;

public class TablaSondeoLineal extends ExploracionTablaHash{
    @Override
    protected int buscarPosicion(Hashable valor) {
        if (!this.estaVacio()) throw new HashTableFullException("No existen mas posiciones disponibles");
        int colision = 0;
        int posicionActual = valor.hash(vector.length);
        int posicioninicial = posicionActual;
        while (vector[posicionActual] != null && !vector[posicionActual].getElemento().equals(valor)){
            posicionActual += 1;
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
