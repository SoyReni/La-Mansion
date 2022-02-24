/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: ComandoDejar.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */

/**
 * Esta clase es un comando que quita un objeto del inventario y lo deja en la
 * habitacion actual.
 * @author Sebastian Caballero
 */
public class ComandoDejar extends ComandoAbstracto {

    /**
     * Intenta dejar un objeto en una habitacion. 
     * 
     */
    @Override
    public boolean ejecutar(Juego juego) {
        if (getPalabras().size() < 2) {
            throw new IllegalArgumentException("Faltan palabras");
        } else {
            String objetoADejar = (String) getPalabras().get(1);
            juego.dejar(objetoADejar);
            return juego.terminar();
        }
    }
}
