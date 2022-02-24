/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: ComandoGuardar.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */

/**
 * Esta clase es un comando que permite tomar un objeto de una habitacion y
 * guardarlo en el inventario
 * 
 * @author Sebastian Caballero
 */
class ComandoGuardar extends ComandoAbstracto {

    public ComandoGuardar() {
    }
    
    /**
     * Intenta guardar un objeto de la habitacion en el inventario.
     *
     */
    @Override
    public boolean ejecutar(Juego juego) {
        if (getPalabras().size() < 2) {
            throw new IllegalArgumentException("Guardar que?");
        } else {
            String objetoAGuardar = (String) getPalabras().get(1);
            juego.guardar(objetoAGuardar);
            return juego.terminar();
        }
    }
}
