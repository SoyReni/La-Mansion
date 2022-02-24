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
 *
 */

import java.io.*;
import java.util.*;

/**
 * Esta clase es parte de Zork. Zork es un simples juego de aventuras basado en
 * texto.
 *
 * "Parser" es una palabra tecnica que se refiere a la parte del programa (por
 * ejemplo un compilador) que descompone texto y lo traduce a una representacion
 * mas manejable.
 *
 * Este Parser lee lo que ingresa el usuario e intenta interpretarlo como un
 * comando "Zork". Cada vez que es llamado, lee una linea de la terminal y lo
 * intenta interpretar como un comando de dos palabras. Retorna el comando como
 * un objeto de tipo Comando.
 *
 * El Parser tiene un conjunto de fabricaDeComandos conocidos. Revisa lo que
 * ingresa el usuario en base a esos fabricaDeComandos conocidos, y si el
 * comando no es conocido, devuelve un objeto de tipo comando que es marcado
 * como comando desconocido.
 *
 *
 */
public class Parser {

    public Parser() {
        fabricaDeComandos = new FabricaDeComandos();
    }

    /**
     * Le pide al usuario que ingrese algo y espera hasta que el usuario lo haga
     * una vez ingresada la linea, se interpreta y se retorna un comando en base
     * a lo interpretado.
     *
     * @return Comando un comando
     */
    public Comando getComando() {

        // contendra la linea entera
        String lineaIngresada = "";
        String palabra1 = null;
        String palabra2 = null;
        String palabra3 = null; //Se agrego una tercera palabra

        System.out.print("> "); // print prompt

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            lineaIngresada = reader.readLine();

            // partir la linea en pedazos con StringTokenizer
            StringTokenizer tokenizer = new StringTokenizer(lineaIngresada);

            if (tokenizer.hasMoreTokens()) {
                palabra1 = tokenizer.nextToken(); // obtener primera palabra
            } else {
                palabra1 = null;
            }
            if (tokenizer.hasMoreTokens()) {
                palabra2 = tokenizer.nextToken(); // obtener segunda palabra
            } else {
                palabra2 = null;
            }
            if (tokenizer.hasMoreTokens()) {
                palabra3 = tokenizer.nextToken(); // obtener segunda palabra
            } else {
                palabra3 = null;
            }


            // dar las palabras a la fabrica para que este crea
            // los comandos respectivos
            return this.fabricaDeComandos.crearComando(palabra1, palabra2, palabra3);

        } catch (IOException exc) {
            System.out.println("Ocurrio un error durante la lectura: "
                    + exc.getMessage());
        }
        // si el comando no es valido o hubo alguna falla retornar el comando "nulo"
        return this.fabricaDeComandos.crearComandoDesconocido();
    }

    //  dado un par de palabras, crea el comando respectivo
    private FabricaDeComandos fabricaDeComandos;
}
