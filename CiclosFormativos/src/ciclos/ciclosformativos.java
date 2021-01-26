package ciclos;

import java.sql.SQLException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dealm
 */
public class ciclosformativos {

    /**
     * salir: cuando se pone en true se acaba el bucle principal y el programa.
     * opcion: recoge la opción seleccionada en el menú del switch.
     * teclado: recoge los valores introducidos por teclado.
     * nombreTabla: guarda el nombre de la tabla de la bbdd.
     * codigo: guarda el código que se asigna al módulo de una tabla.
     * nombre: guarda el nombre asignado al módulo de una tabla.
     * horas: guarda las horas asignadas al módulo de una tabla.
     */
    public static boolean salir = false;
    public static int opcion;
    public static Scanner teclado = new Scanner(System.in);
    public static String nombreTabla;
    public static String codigo;
    public static String nombre;
    public static String horas;

    public static void main(String[] args) throws SQLException {
        
        /**
         * el valor teclado recoge todos los bytes introducidos hasta que exista un salto de línea.
         */
        teclado.useDelimiter("\n");

        System.out.println("************Bienvenida/os al programa de gestión de ciclos formativos************");
        
        /**
         *conectar instancia una nueva conexión.
         */
        Conexion conectar = new Conexion();
        /**
         * bucle que se repite hasta que la variable salir sea true. 
         */
        while (!salir) {
            System.out.println("Seleccione una de las siguientes opciones: \n1.Crear nuevo ciclo \n2.Insertar módulo  \n3.Eliminar módulo \n4.Eliminar ciclo \n5.Salir");
            opcion = teclado.nextInt();
            /**
             * ejecuta distintas funciones según la opción recogida.
             */
            switch (opcion) {
                /**
                 * crea tabla nueva.
                 */
                case 1:
                    System.out.println("Introduzca el nombre del nuevo ciclo formativo:");
                    nombreTabla = teclado.next();
                    conectar.crearTabla(nombreTabla);
                    break;
                /**
                 * crea módulo nuevo indicando la tabla donde se quiere crear. 
                 */
                case 2:
                    System.out.println("Introduzca el nombre del ciclo en el que introducir el nuevo módulo:");
                    nombreTabla = teclado.next();
                    System.out.println("Introduzca el código del módulo:");
                    codigo = teclado.next();
                    System.out.println("Introduzca el nombre del módulo:");
                    nombre = teclado.next();
                    System.out.println("Introduzca las horas del módulo:");
                    horas = teclado.next();
                    conectar.insertarModulo(nombreTabla, codigo, nombre, horas);
                    break;
                /**
                 * elimina un módulo indicando la tabla en la que se encuentra y el código del mismo.
                 */
                case 3:
                    System.out.println("Introduzca el nombre del ciclo en el que se encuentra el módulo a eliminar:");
                    nombreTabla = teclado.next();
                    System.out.println("Introduzca el código del módulo que desea eliminar:");
                    codigo = teclado.next();
                    conectar.eliminarModulo(nombreTabla, codigo);
                    break;
                /**
                 * elimina una tabla.
                 */
                case 4:
                    System.out.println("Introduzca el nombre del ciclo formativo a eliminar:");
                    nombreTabla = teclado.next();
                    conectar.eliminarTabla(nombreTabla);
                    break;
                    /**
                     * fin de las opciones y del programa
                     */
                case 5:
                    salir = true;
                    conectar.desconectar();
                    System.out.println("************FIN DEL PROGRAMA************");
                    break;
                /**
                 * indica error en el valor introducido por teclado
                 */   
                default:
                    System.out.println("El valor introducido no es correcto, por favor inténtelo de nuevo");
                    break;
            }
        }
        
    }

}
