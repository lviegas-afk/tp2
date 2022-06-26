package estructuraDatos;

public abstract class ExploracionTablaHash implements TablaHash{
    private static final int tamanio_tabla_defecto = 10;

    protected EntradaHash[] vector;

    private int tamanio_actual;

    private final void crearVector( int tamanioVector){
        this.vector = new EntradaHash[tamanioVector];
    }

    public ExploracionTablaHash(){
        this.crearVector(tamanio_tabla_defecto);
        this.vaciar();
    }

    public ExploracionTablaHash(int tamanio){
        this.crearVector(tamanio);
        this.vaciar();
    }

    @Override
    public final void vaciar() {
        this.tamanio_actual = 0;
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = null;
        }
    }

    @Override
    public void insertar(Hashable valor){
        int posicion = buscarPosicion(valor);
        System.out.println("Se agrega el elemento " + valor.toString() + " a la posicion " + posicion );
        this.vector[posicion] = new EntradaHash(valor,true);
    }

    @Override
    public void eliminar(Hashable valor){
        int posicion = buscarPosicion(valor);
        this.vector[posicion] = null;

    }

    @Override
    public EntradaHash buscar(Hashable valor){
        int posicion = buscarPosicion(valor);
        System.out.println("Se encontro el valor en el indice " + Integer.parseInt(String.valueOf(posicion)));
        return this.vector[posicion];
    }

    @Override
    public boolean estaVacio() {
        for (int i = 0; i < vector.length; i++) {
            if (this.vector[i] == null) {
                return true;
            }
        }
        return false;

    }

    protected abstract int buscarPosicion(Hashable valor);

    public void imprimirTablaHash(){
        System.out.println(" | POSICION | VALOR |");
    }
    public int getTamanioTablaDefault(){
        return this.tamanio_tabla_defecto;
    }
}
