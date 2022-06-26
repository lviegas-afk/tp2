import estructuraDatos.*;
import excepciones.HashTableFullException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {

    static Scanner scanInput = new Scanner(System.in);
    public static void demo(){
        TablaSondeoCuadratico tablaHashCuadratica = new TablaSondeoCuadratico();
        TablaSondeoLineal tablaSondeoLineal = new TablaSondeoLineal();
        TablaHashingAbierto tablaHashingAbierto = new TablaHashingAbierto();
        Recurso[] recursos = new Recurso[11];
        recursos[0] = new Recurso(1152692);
        recursos[1] = new Recurso(111254);
        recursos[2] = new Recurso(3754223);
        recursos[3] = new Recurso(4354153);
        recursos[4] = new Recurso(115269);
        recursos[5] = new Recurso(1151254);
        recursos[6] = new Recurso(564223);
        recursos[7] = new Recurso(3141553);
        recursos[8] = new Recurso(3342423);
        recursos[9] = new Recurso(3534153);
        recursos[10] = new Recurso(3234153);

        System.out.println("Se agregan los recursos en la tabla has con manejo de coliciones cuadratico");
        try {
            for (int i = 0; i < recursos.length; i++) {
                tablaHashCuadratica.insertar(recursos[i]);
            }
        } catch (HashTableFullException error){
            System.out.println(error.getMessage());
        } catch (ArrayIndexOutOfBoundsException error){
            System.out.println(error.getMessage());
        }
        System.out.println("Se agregan los recursos en la tabla has con manejo de coliciones lineal");
        try{
            for (int i = 0; i < recursos.length; i++) {
                tablaSondeoLineal.insertar(recursos[i]);
            }
        } catch (HashTableFullException error){
            System.out.println(error.getMessage());
        } catch (ArrayIndexOutOfBoundsException error){
            System.out.println(error.getMessage());
        }

        for (int i = 0; i < recursos.length; i++) {
            tablaHashingAbierto.insertar(recursos[i]);
        }

        System.out.println("Tabla hash sondeo cuadratico:");
        tablaHashCuadratica.imprimirTablaHash();
        System.out.println("Tabla hash sondelo lineal:");
        tablaSondeoLineal.imprimirTablaHash();
        System.out.println("Tabla hash con hashing abierto:");
        tablaHashingAbierto.imprimirTablaHash();
    }

    public static void main(String[] args) {
        String data;
        boolean salir = false;
        do {
            System.out.println("Opciones:\n" +
                    "\tSeleccione el metodo deseado:\n" +
                    "\t\t1- Operar con Tabla Hash de manejo de colisiones cuadratico\n" +
                    "\t\t2- Operar con Tabla Hash de manejo de colisiones con sondeo lineal\n" +
                    "\t\t3- Operar con Tabla Hash de manejo de colisiones de Direccionamiento cerrado (hashing abierto)\n" +
                    "\t4- Demo\n" +
                    "\t5- Salir\n");

            data = scanInput.nextLine();
            try {
                switch (data){
                    case "1":
                        System.out.println("Caso de hash cuadratico");
                        ejecutar(TablaSondeoCuadratico.class);
                        break;
                    case "2":
                        System.out.println("Caso de hash lineal");
                        ejecutar(TablaSondeoLineal.class);
                        break;
                    case "3":
                        System.out.println("Caso de hash abierto");
                        ejecutar(TablaHashingAbierto.class);
                        break;
                    case "4":
                        demo();
                        break;
                    case "5":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion incorrecta, por favor seleccione una opcion del 1 al 5");
                        break;
                }
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } while  (!salir);
        scanInput.close();
        System.out.println("Bye!");
    }

    public static int imprimirMenu(String tipoHash){
        String dato;
        System.out.println("Opciones:\n" +
                "\t1- Insertar elemento\n" +
                "\t2- Buscar elemento\n" +
                "\t3- Borrar elemento\n" +
                "\t4- Imprmir tabla hash completa\n" +
                "\t5- Atras\n");
        dato = scanInput.nextLine();
        return Integer.parseInt(dato);
    }

    public static String capturarConLeyenda(String leyenda){
        System.out.println(leyenda);
        String valor = scanInput.nextLine();
        return valor;
    }

    public static Hashable obtenerObjetoHasheableConLeyenda(String leyenda){
        String valor = capturarConLeyenda(leyenda);
        Hashable object = new Recurso(Integer.parseInt(valor));
        return object;
    }

    public static void ejecutar(Class claseHash) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        TablaHash tabla;
        Constructor<TablaHash> constructor = null;
        constructor = claseHash.getConstructor();
        System.out.println("Seleccione el tamaño de la tabla hash ( escriba 'D' para default ) :");
        String dato;
        dato = scanInput.nextLine();
        if(dato.toUpperCase().equals("D")){
            tabla = constructor.newInstance();
            System.out.println("Se utilizara el tamaño default configurado para la tabla: " + tabla.getTamanioTablaDefault());
        } else {
            tabla = constructor.newInstance(Integer.parseInt(dato));
        }
        int opcion;
        boolean otro;
        do {
            opcion = imprimirMenu("");
            switch (opcion) {
                case 1:
                    do {
                        otro = false;
                        tabla.insertar(obtenerObjetoHasheableConLeyenda("Ingrese el valor a insertar (numerico):"));
                        System.out.println("Desea insertar otro (s/N)");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 2:
                    do {
                        otro = false;
                        tabla.buscar(obtenerObjetoHasheableConLeyenda("Ingrese un valor que desea buscar"));
                        System.out.println("Desea buscar otro (s/N)");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 3:
                    do {
                        otro = false;
                        tabla.eliminar(obtenerObjetoHasheableConLeyenda("Ingrese un valor que desea eliminar"));
                        System.out.println("Desea borrar otro (s/N)");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 4:
                    tabla.imprimirTablaHash();
                    break;
            }
        } while(opcion != 5);
    }
}
