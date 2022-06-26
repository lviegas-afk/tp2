package estructuraDatos;

public interface TablaHash {
    void insertar(Hashable valor);
    void eliminar(Hashable valor);
    EntradaHash buscar(Hashable valor);
    void vaciar();
    boolean estaVacio();
    int getTamanioTablaDefault();
    void imprimirTablaHash();
}
