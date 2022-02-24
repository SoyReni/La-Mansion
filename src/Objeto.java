/**
 * Alumno: Sebastian Caballero. 
 * Nombre de archivo: Objeto.java 
 * Introduccion a la Programacion II. 
 * FIUNI 2019.
 */

/**
 * Clase que representa los objetos con los que se pueden interactuar en el
 * juego.
 *
 * @author Sebastian Caballero
 */
public class Objeto {

    /**
     * Crea un objeto de tipo Objeto
     *
     * @param nombre String nombre del objeto
     * @param peso double peso del objeto
     * @param danio in daño que infringe el objeto
     */
    public Objeto(String nombre, double peso, int danio) {
        _Nombre = nombre;
        _Peso = peso;
        _Danio = danio;
    }

    /**
     * Metodo que retorna el nombre del objeto
     *
     * @return String nombre del objeto
     */
    public String getNombre() {
        return _Nombre;
    }

    /**
     * Metodo que retorna el peso del objeto
     *
     * @return double peso del objero
     */
    public double getPeso() {
        return _Peso;
    }

    /**
     * Metodo que retorna el daño que infringe el objeto
     *
     * @return int daño que infringe objeto
     */
    public int getDanio() {
        return _Danio;
    }

    private String _Nombre;
    private final double _Peso;
    private final int _Danio;

}
