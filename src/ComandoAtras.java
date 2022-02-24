/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: ComandoVolver.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */

/**
 * Esta clase es un comando que cambia el cuarto del jugador del juego por
 * el anterior en el que se encontraba.
 * @author Sebastian Caballero
 * 
 */
class ComandoAtras extends ComandoAbstracto {

    public ComandoAtras() {
    }
    /**
     * Intenta volver a la habitacion anterior.
     *  
     */
    @Override
    public boolean ejecutar(Juego juego) {
        juego.atras();
        return juego.terminar();
    }

}
