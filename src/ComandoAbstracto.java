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

import java.util.*;

/**
 *
 * Esta clase implementa lo basico de la interfaz Comando
 *
 */
public abstract class ComandoAbstracto implements Comando, Cloneable {

    private List palabras;

    /**
     * Le dice al comando cuales son las palabras del comando.
     * Ej: Lista con "ir" y "norte"
     *
     * La primera palabra siempre es el comando mismo.
     *
     * @param palabras las palabras utilizadas en el comando
     */
    @Override
    public void setPalabras(List palabras) {
        if (null == palabras) {
            throw new IllegalArgumentException(
                "La lista no puede ser nula.. pero si vacia");
        }
        this.palabras = Collections.unmodifiableList(palabras);
    }

    /**
     * Retorna las palabras utilizadas en el comando. Ej:
     *  Lista con "ir" y "norte"
     * 
     * @return 
     */
 
    public List getPalabras() {
        //se modifico la cantidad de palabras para admitir comandos de tres palabras
        if (null == this.palabras || this.palabras.size() < 3) {
            throw new IllegalStateException("Palabras no inicializadas correctamente");
        }
        return this.palabras;
    }
    

    /**
     * Devuelve una copia del comando
     *
     * @return Comando
     */
    @Override
    public Comando copiar() {
        try {
            return (Comando) clone();
        } catch (CloneNotSupportedException ex) {
            // tonteria del API de Java
            throw new IllegalStateException("No se puede clonear", ex);
        }
    }

}
