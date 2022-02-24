
/**
 * Alumno: Sebastian Caballero. 
 * Nombre de archivo: Luz.java 
 * Introduccion a la Programacion II. FIUNI 2019.
 */

/**
 * Esta clase es un objeto que tiene una luz para ver lo que hay en las
 * habitaciones
 *
 * @author Sebastian Caballero
 */
public class Luz extends Objeto {

    /**
     * Crea un objeto de tipo Luz
     *
     * @param nombre String nombre del objeto
     * @param peso double peso del objeto
     * @param danio int daño que infringe el objeto
     * @param iluminacion int cantidad de pasos que dura la luz
     * @param encendido boolean indica si la luz esta encendida
     */
    public Luz(String nombre, double peso, int danio, int iluminacion, boolean encendido) {
        super(nombre, peso, danio);
        _Iluminacion = iluminacion;
        _Encendido = encendido;
    }

    /**
     * Metodo que retorna la cantidad de pasos que dura una luz
     *
     * @return int cantidad de pasos que dura la luz
     */
    public int getIluminacion() {
        return _Iluminacion;
    }

    /**
     * Metodo para determinar si una luz esta encendida o no este metodo debe
     * ser llamado cuando se da el paso de una habitacion a otra
     */
    public void setIluminacion() {
        //si se da un paso, la iluminacion baja
        _Iluminacion--;
        //si la iluminacion llega a 0, la luz se apaga
        if (0 >= _Iluminacion) {
            _Encendido = false;
        }
    }

    /**
     * Metodo para saber si la luz esta encendida o apagada
     *
     * @return boolean devuelve true si esta encendido
     */
    public boolean isEncendido() {
        return _Encendido;
    }

    private int _Iluminacion;
    private boolean _Encendido;
}
