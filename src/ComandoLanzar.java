/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: ComandoLanzar.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */

/**
 * Esta clase es un comando que permite lanzar un objeto del inventario a un 
 * enemigo infringiendo daño
 * 
 * @author Sebastian Caballero
 */
class ComandoLanzar extends ComandoAbstracto {

    public ComandoLanzar() {
    }
    /**
     * Intenta lanzar un objeto a un enemigo. 
     *  
     */
    @Override
    public boolean ejecutar(Juego juego) {
            String arma = (String) getPalabras().get(1);
            String enemigo = (String) getPalabras().get(2);
            juego.lanzar(arma, enemigo);
            return juego.terminar();
    }
}
