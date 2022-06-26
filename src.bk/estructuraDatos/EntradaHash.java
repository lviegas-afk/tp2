package estructuraDatos;

public class EntradaHash {
    private Hashable elemento;
    private boolean activo;
    private EntradaHash anterior = null;
    private EntradaHash siguiente = null;

    public EntradaHash(Hashable valor, boolean b){
        this.elemento = valor;
        this.activo = b;
    }

    public EntradaHash(Hashable valor){
        this.elemento = valor;
        this.activo = true;
    }

    public Hashable getElemento() {
        return this.elemento;
    }

    public void setActivo(boolean b) {
        this.activo = b;
    }

    public EntradaHash getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(EntradaHash siguiente) {
        this.siguiente = siguiente;
    }

    public EntradaHash getAnterior() {
        return anterior;
    }

    public void setAnterior(EntradaHash anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return this.elemento.toString();
    }

}
