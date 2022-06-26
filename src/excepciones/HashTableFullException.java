package excepciones;

public class HashTableFullException extends RuntimeException{
    public HashTableFullException(String msgError){
        super(msgError);
    }
}
