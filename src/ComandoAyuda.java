/*
 * Universidad Nacional de Itapua.
 * Proyecto Zork.
 *
 * Autor Original: Michael Kolling, Universidad de Monash
 * Version: 1.1
 * Date: March 2000
 * Copyright (c) Michael Kolling
 *
 * Nombre del Alumno: Sebastian Caballero
 * Esta clase fue modificada
 *
 */

import java.util.*;

/**
 * Esta clase implementa la ayuda para el juego
 */
public class ComandoAyuda extends ComandoAbstracto {

    /**
     * Imprime a pantalla alguna informacion de ayuda.
     *
     */
    public boolean ejecutar(Juego juego) {
        System.out.println(CABEZA_PIE);
        System.out.printf(FORMATO,"Lista de comandos disponibles");
        System.out.printf(FORMATO,"Ir: [ir habitacion] para ir a la habitacion indicada si es posible.");
        System.out.printf(FORMATO,"Guardar: [guardar objeto] para guardar un objeto de la habitacion en tu inventario.");
        System.out.printf(FORMATO,"Dejar: [dejar objeto] para dejar un objeto de tu inventario en esta habitacion.");
        System.out.printf(FORMATO,"Abrir: [abrir habitacion llave] para abrir la habitacion indicada, si esta cerrada.");
        System.out.printf(FORMATO,"Lanzar: [lanzar objeto enemigo] para lanzar el objeto indicado al enemigo.");
        System.out.printf(FORMATO,"Mapa: [mapa] para ver el mapa del piso en el que te encuentras.");
        System.out.printf(FORMATO,"Volver: [volver] para volver a la habitacion anterior.");
        System.out.printf(FORMATO,"Salir: [salir] para salir del juego");
        System.out.println(CABEZA_PIE);
        System.out.printf(FORMATO,"Tips:");
        System.out.printf(FORMATO,"1. Las llaves, la sal y la luz son MUY importantes");
        System.out.printf(FORMATO,"2. Piensa que objetos quieres llevar contigo, no puedes cargar con todos los que hay");
        System.out.printf(FORMATO,"3. La luz no dura para siempre.");
        System.out.printf(FORMATO,"4. Harrin, mata a Harrin...");
        System.out.println(CABEZA_PIE);
        // imprimir todos los comandos
        FabricaDeComandos fabrica = new FabricaDeComandos();
        Collection listaComandos = fabrica.comandosConocidos();
        for (Iterator iter = listaComandos.iterator(); iter.hasNext(); ) {
            System.out.print(" ");
            System.out.print( (String) iter.next());
        }
        // no parar el juego
        return true;
    }
    //Formatos de los textos en pantalla
    private final String FORMATO = "| %-90s|\n";
    private final String CABEZA_PIE = "=============================================================================================";

}
