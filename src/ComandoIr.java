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

/**
 * Esta clase es un comando que cambia el cuarto actual del jugador del
 * juego.
 *
 */
public class ComandoIr extends ComandoAbstracto {

    /**
     * Intenta ir en la direccion indicada.
     *
     */
    @Override
    public boolean ejecutar(Juego juego) {
        if (getPalabras().size() < 2) {
            throw new IllegalArgumentException("Faltan palabras");
        }
        String direccion = (String) getPalabras().get(1);
        juego.irA(direccion);
        return juego.terminar();
    }
}
