/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: ComandoAbrir.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */

/**
 * Esta clase es un comando para abrir una habitacion que esta cerrada si 
 * se dispone de una llave
 * @author Sebastian Caballero
 */
public class ComandoAbrir extends ComandoAbstracto {

    /**
     * Intenta abrir la habitacion indicada (si es que esta cerrada)
     *
     */
    @Override
    public boolean ejecutar(Juego juego) {
        if (getPalabras().size() < 3) {
            throw new IllegalArgumentException("Faltan palabras");
        }
        String puerta = (String) getPalabras().get(1); 
        String objeto = (String) getPalabras().get(2);
        juego.abrir(puerta, objeto);
        return juego.terminar();
    }

}
