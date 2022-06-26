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

        System.out.println("\n\033[1m" + "Se agregan los recursos en la tabla hash con manejo de colisiones cuadratico." + "\033[0m");
        try {
            for (int i = 0; i < recursos.length; i++) {
                tablaHashCuadratica.insertar(recursos[i]);
            }
        } catch (HashTableFullException error){
            System.out.println(error.getMessage());
        } catch (ArrayIndexOutOfBoundsException error){
            System.out.println(error.getMessage());
        }
        System.out.println("\n\033[1m" + "Se agregan los recursos en la tabla hash con manejo de colisiones lineal." + "\033[0m");
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

        System.out.println("\n\033[1m" + "Tabla hash sondeo cuadratico:" + "\033[0m");
        tablaHashCuadratica.imprimirTablaHash();
        System.out.println("\n\033[1m" + "Tabla hash sondelo lineal:" + "\033[0m");
        tablaSondeoLineal.imprimirTablaHash();
        System.out.println("\n\033[1m" + "Tabla hash con hashing abierto:" + "\033[0m");
        tablaHashingAbierto.imprimirTablaHash();
    }

    public static void main(String[] args) {
        String data;
        boolean salir = false;
        do {
            System.out.println("\n\033[1m" + "Opciones:" + "\033[0m\n" +
                    "\t\033[1m" + "Seleccione el metodo deseado:\n" + "\033[0m" +
                    "\t\t\033[1m" + "1-" + "\033[0m" + " Operar con tabla hash de manejo de colisiones cuadratico\n" +
                    "\t\t\033[1m" + "2-" + "\033[0m" + " Operar con tabla hash de manejo de colisiones con sondeo lineal\n" +
                    "\t\t\033[1m" + "3-" + "\033[0m" + " Operar con tabla hash de manejo de colisiones de direccionamiento cerrado (hashing abierto)\n" +
                    "\t\t\033[1m" + "4-" + "\033[0m" + " Demo\n" +
                    "\t\t\033[1m" + "5-" + "\033[0m" + " Salir");

            System.out.print("\033[1m" + "> " + "\033[0m");
            data = scanInput.nextLine();
            try {
                switch (data){
                    case "1":
                        System.out.println("\033[3m" + "Caso de hash cuadratico." + "\033[0m\n");
                        ejecutar(TablaSondeoCuadratico.class);
                        break;
                    case "2":
                        System.out.println("\033[3m" + "Caso de hash lineal." + "\033[0m\n");
                        ejecutar(TablaSondeoLineal.class);
                        break;
                    case "3":
                        System.out.println("\033[3m" + "Caso de hash abierto." + "\033[0m\n");
                        ejecutar(TablaHashingAbierto.class);
                        break;
                    case "4":
                        demo();
                        break;
                    case "5":
                        salir = true;
                        break;
                    default:
                        System.out.println("\033[1m" + "Opcion incorrecta, por favor seleccione una opcion del 1 al 5" + "\033[0m");
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
        System.out.println("\033[1m" + "Bye!" + "\033[0m");
    }

    public static int imprimirMenu(String tipoHash){
        String dato;
        System.out.println("\n\033[1m" + "Opciones:" + "\033[0m\n" +
                "\t\033[1m" + "1-" + "\033[0m" + " Insertar elemento\n" +
                "\t\033[1m" + "2-" + "\033[0m" + " Buscar elemento\n" +
                "\t\033[1m" + "3-" + "\033[0m" + " Borrar elemento\n" +
                "\t\033[1m" + "4-" + "\033[0m" + " Imprimir tabla hash completa\n" +
                "\t\033[1m" + "5-" + "\033[0m" + " Atras");

        System.out.print("\033[1m" + "> " + "\033[0m");
        dato = scanInput.nextLine();
        return Integer.parseInt(dato);
    }

    public static String capturarConLeyenda(String leyenda){
        System.out.println(leyenda);
        System.out.print("\033[1m" + "> " + "\033[0m");
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
        System.out.println("\033[1m" + "Seleccione el tamaño de la tabla hash (escriba 'D' para default):" + "\033[0m");
        String dato;
        System.out.print("\033[1m" + "> " + "\033[0m");
        dato = scanInput.nextLine();
        if(dato.toUpperCase().equals("D")){
            tabla = constructor.newInstance();
            System.out.println("\033[3m" + "Se utilizara el tamaño default configurado para la tabla: " + tabla.getTamanioTablaDefault() + "." + "\033[0m");
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
                        tabla.insertar(obtenerObjetoHasheableConLeyenda("\n\033[1m" + "Ingrese el valor a insertar (numerico):" + "\033[0m"));
                        System.out.println("\033[1m" + "Desea insertar otro? (s/N)"+ "\033[0m");
                        System.out.print("\033[1m" + "> " + "\033[0m");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 2:
                    do {
                        otro = false;
                        tabla.buscar(obtenerObjetoHasheableConLeyenda("\n\033[1m" + "Ingrese el valor que desea buscar" + "\033[0m"));
                        System.out.println("\n\033[1m" + "Desea buscar otro? (s/N)" + "\033[0m");
                        System.out.print("\033[1m" + "> " + "\033[0m");
                        if(scanInput.nextLine().toUpperCase().equals("S")){
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 3:
                    do {
                        otro = false;
                        tabla.eliminar(obtenerObjetoHasheableConLeyenda("\n\033[1m" + "Ingrese el valor que desea eliminar" + "\033[0m"));
                        System.out.println("\n\033[1m" + "Desea borrar otro? (s/N)" + "\033[0m");
                        System.out.print("\033[1m" + "> " + "\033[0m");
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
