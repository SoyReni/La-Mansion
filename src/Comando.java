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
 * Esta clase no fue modificada por el alumno
 */

import java.util.*;

/**
 * Esta interface representa el comando emitido por el usuario.
 *
 */

public interface Comando {

    /**
     * Ejecuta el comando sobre un juego dado. Este metodo
     * modifica al juego (generalmente).
     *
     * @param juego el juego en el que se esta ejecutando
     * @return true si el juego debe continuar, false si no
     */
    public boolean ejecutar(Juego juego);

    /**
     * Le dice al comando cuales son las palabras del comando.
     * Ej: Lista con "ir" y "norte"
     *
     * La primera palabra siempre es el comando mismo.
     *
     * @param palabras las palabras utilizadas en el comando
     */
    public void setPalabras(List palabras);

    /**
     * Retorna las palabras utilizadas en el comando. Ej:
     *  Lista con "ir" y "norte"
     */
    public List getPalabras();

    /**
     * Devuelve una copia del comando
     *
     * @return Comando
     */
    public Comando copiar();

}
