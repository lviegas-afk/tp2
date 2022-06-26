package estructuraDatos;

public class TablaHashingAbierto extends ExploracionTablaHash{

    int saltos;
    @Override
    protected int buscarPosicion(Hashable valor) {
        return valor.hash(vector.length);
    }

    @Override
    public void imprimirTablaHash() {
        super.imprimirTablaHash();
        for (int i = 0; i < this.vector.length ; i++) {
            String valor;
            if (this.vector[i] == null) {
                 valor = null;
            } else {
                EntradaHash siguiente = this.vector[i];
                valor = "";
                while (siguiente != null) {
                    valor = valor + siguiente.toString() + " ";
                    siguiente = siguiente.getSiguiente();
                }
            }
            if (valor == null) {
                System.out.println(" | " + i + " | " + "\033[3m" + "Libre" + "\033[0m |");
            }
            else {
                System.out.println(" | " + i + " | " + valor + "|");
            }
        }
    }

    @Override
    public void insertar(Hashable valor){
        this.saltos = 1;
        int posicion = this.buscarPosicion(valor);
        if (this.vector[posicion] == null){
            this.vector[posicion] = new EntradaHash(valor,true);
        } else {
            this.insertarEn(valor, this.vector[posicion]);
        }
        System.out.println("\033[3m" + "Se agrega el elemento " + valor.toString() + " a la posicion " + posicion + " numero de elemento dentro de la posicion " + this.saltos + "." + "\033[0m");
    }

    private void insertarEn(Hashable valor,EntradaHash entrada){
        this.saltos++;
        if(entrada.getSiguiente() == null){
            EntradaHash nuevaEntrada = new EntradaHash(valor,true);
            entrada.setSiguiente(nuevaEntrada);
            nuevaEntrada.setAnterior(entrada);
        } else {
            this.insertarEn(valor, entrada.getSiguiente());
        }
    }

    @Override
    public void eliminar(Hashable valor) {
        int posicion = buscarPosicion(valor);
        EntradaHash resultado = this.buscar(valor);
        if(resultado != null){
            resultado.setActivo(false);
            if (resultado.getAnterior() == null ){
                EntradaHash nuevaraiz = this.vector[posicion].getSiguiente();
                this.vector[posicion] = nuevaraiz;
                nuevaraiz.setAnterior(null);
            } else {
                resultado.setActivo(false);
                if (resultado.getSiguiente() != null)
                    resultado.getSiguiente().setAnterior(resultado.getAnterior());
                if (resultado.getAnterior() != null)
                    resultado.getAnterior().setSiguiente(resultado.getSiguiente());
            }
        } else {
            System.out.println("\033[3m" + "No existe el valor dentro de la tabla hash." + "\033[0m");
        }
    }

    @Override
    public EntradaHash buscar(Hashable valor){
        int posicion = buscarPosicion(valor);
        EntradaHash respuesta = null;
        if (this.vector[posicion] ==null) {
            System.out.println("\033[3m" + "No existe el valor dentro de la tabla hash." + "\033[0m");
            return null;
        }
        System.out.println("Se encontro el valor en el indice " + Integer.parseInt(String.valueOf(posicion)));
        if (this.vector[posicion].getElemento().equals(valor)){
            System.out.println(this.vector[posicion].getElemento());
            return this.vector[posicion];
        } else {
            if (this.vector[posicion].getSiguiente() != null)
                respuesta = this.buscar(valor, this.vector[posicion].getSiguiente());
        }
        if(respuesta == null){
            System.out.println("\033[3m" + "No existe el valor dentro de la tabla hash." + "\033[0m");
        }
        return respuesta;
    }

    private EntradaHash buscar(Hashable valor, EntradaHash entradaHash) {
        if (entradaHash.getElemento().equals(valor)){
            System.out.println(entradaHash.getElemento());
            return entradaHash;
        } else {
            if (entradaHash.getSiguiente() != null)
                return this.buscar(valor, entradaHash.getSiguiente());
        }
        return null;
    }

}
