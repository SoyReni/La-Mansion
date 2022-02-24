
/**
 * Alumno: Sebastian Caballero. 
 * Nombre de archivo: Habitacion.java Introduccion
 * a la Programacion II. FIUNI 2019.
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Clase basada en la clase Cuarto proporcionada por el docente
 *
 * Una habitacion representa una ubicacion en el escenario del juego..
 *
 * Las habitaciones estan intercontectados por medio de salidas, puede contener
 * seis objetos y un enemigo
 *
 * @author Sebastian Caballero
 */
public class Habitacion {

    /**
     * Crea un nuevo objeto Habitacion
     *
     * @param nombre String nombre de la habitacion
     * @param descripcion String descripcion breve de la habitacion
     * @param cerrado boolean parametro que indica si la habitacion esta cerrada
     * o no
     */
    public Habitacion(String nombre, String descripcion, boolean cerrado) {
        _Nombre = nombre;
        _Descripcion = descripcion;
        objetos = new HashMap<>();
        salidas = new HashMap<>();
        enemigo = null;
        _EstaCerrado = cerrado;

    }

    /**
     * Metodo para determinar cuales son las salidas disponibles de la
     * habitacion
     *
     * @param salida1 Habitacion primera salida de la habitacion
     * @param salida2 Habitacion segunda salida de la habitacion
     * @param salida3 Habitacion tercera salida de la habitacion
     * @param salida4 Habitacion cuarta salida de la habitacion
     * @param salida5 Habitacion quinta salida de la habitacion
     */
    public void setSalidas(Habitacion salida1, Habitacion salida2, Habitacion salida3, Habitacion salida4, Habitacion salida5) {
        if (salida1 != null) {
            salidas.put(salida1.getNombre(), salida1);
        }
        if (salida2 != null) {
            salidas.put(salida2.getNombre(), salida2);
        }
        if (salida3 != null) {
            salidas.put(salida3.getNombre(), salida3);
        }
        if (salida4 != null) {
            salidas.put(salida4.getNombre(), salida4);
        }
        if (salida5 != null) {
            salidas.put(salida5.getNombre(), salida5);
        }
    }

    /**
     * Metodo para determinar cuales son los objetos que estan en la habitacion
     *
     * @param objeto1 Objeto primer objeto de la habitacion
     * @param objeto2 Objeto segundo objeto de la habitacion
     * @param objeto3 Objeto tercer objeto de la habitacion
     * @param objeto4 Objeto cuarto objeto de la habitacion
     * @param objeto5 Objeto quinto objeto de la habitacion
     * @param objeto6 Objeto sexto objeto de la habitacion
     */
    public void setObjetos(Objeto objeto1, Objeto objeto2, Objeto objeto3, Objeto objeto4, Objeto objeto5, Objeto objeto6) {
        if (objeto1 != null) {
            objetos.put(objeto1.getNombre(), objeto1);
        }
        if (objeto2 != null) {
            objetos.put(objeto2.getNombre(), objeto2);
        }
        if (objeto3 != null) {
            objetos.put(objeto3.getNombre(), objeto3);
        }
        if (objeto4 != null) {
            objetos.put(objeto4.getNombre(), objeto4);
        }
        if (objeto5 != null) {
            objetos.put(objeto5.getNombre(), objeto5);
        }
        if (objeto6 != null) {
            objetos.put(objeto6.getNombre(), objeto6);
        }
    }

    /**
     * Metodo para determinar el enemigo que se encuentra en la habitacion.
     *
     * @param enemigoNuevo Enemigo enemigo que se ubica en la habitacion
     */
    public void setEnemigo(Enemigo enemigoNuevo) {
        enemigo = enemigoNuevo;
    }

    /**
     * Metodo que retorna el nombre de la habitacion
     *
     * @return String nombre de la habitacion.
     */
    public String getNombre() {
        return this._Nombre;
    }

    /**
     * Metodo que retorna la descripcion de la habitacion
     *
     * @return String descripcion de la habitacion.
     */
    public String getDescripcion() {
        return "Ahora te encuentras en " + _Descripcion + "\n" + textoSalidas();
    }

    /**
     * Metodo que retorna la coleccion de objetos disponibles en la habitacion
     *
     * @return HashMap la coleccion de objetos de la habitacion.
     */
    public HashMap getObjetos() {
        return objetos;
    }

    /**
     * Metodo para saber si la habitacion esta cerrada o no
     *
     * @return boolean devuelve false si la habitacion esta cerrada
     */
    public boolean estaCerrado() {
        return false != _EstaCerrado;
    }

    /**
     * Metodo para abrir la habitacion.
     */
    public void abrir() {
        _EstaCerrado = false;
    }

    /**
     * Metodo que retorna una salida de la habitacion
     *
     * @param habitacion String que representa la habitacion que necesitamos
     * @return Habitacion devuelve la habitacion que necesitamos
     */
    public Habitacion getSalida(String habitacion) {
        return salidas.get(habitacion);
    }

    /**
     * Metodo que retorna un String con las salidas de la habitacion
     *
     * @return String devuelve las salidas de la habitacion.
     */
    private String textoSalidas() {
        String resultado = "Salidas:";
        Set keys = salidas.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            resultado += " " + iter.next();
        }
        return resultado;
    }

    /**
     * Metodo que retorna un String con los objetos de la habitacion
     *
     * @return String objetos de la habitacion
     */
    public String textoObjetos() {
        String resultado = "Objetos disponibles:";
        Set keys = objetos.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            resultado += " " + iter.next();
        }
        return resultado;
    }

    /**
     * Metodo para quitar un objeto de la habitacion, que luego sera guardado en
     * el inventario
     *
     * @param key String key del objeto a quitar
     */
    public void quitarObjeto(String key) {
        objetos.remove(key);
    }

    /**
     * Metodo para dejar objetos en la habitacion
     *
     * @param key String key del objeto a dejar
     * @param value String value del objeto a dejar
     */
    public void dejarObjeto(String key, Objeto value) {
        objetos.put(key, value);
    }

    /**
     * Metodo que retorna un String con el enemigo de la habitacion, aunque no
     * haya ninguno.
     *
     * @return String con el enemigo de la habitacion
     */
    public String textoEnemigos() {
        String resultado = "Enemigo: ";
        if (this.hayEnemigo()) {
            return resultado + this.enemigo.getNombre();
        } else {
            return resultado + "ninguno";
        }
    }

    /**
     * Metodo para aislar un objeto de la habitacion para luego sacarla
     *
     * @param objeto String que representa el objeto a aislar
     * @return Objeto objeto a aislar
     */
    public Objeto separarObjeto(String objeto) {
        return objetos.get(objeto);
    }

    /**
     * Metodo para saber si en la habitacion hay enemigo
     *
     * @return boolean devuelve true si no hay enemigo en la habitacion.
     */
    public boolean hayEnemigo() {
        return null != enemigo;
    }

    /**
     * Metodo que retorna el enemigo de la habitacion
     *
     * @return Enemigo enemigo de la habitacion
     */
    public Enemigo getEnemigo() {
        return enemigo;
    }

    public Habitacion siguienteHabitacion(String direccion) {
        return (Habitacion) salidas.get(direccion);
    }
    
    
    private boolean _EstaCerrado;
    private String _Nombre;
    private String _Descripcion;
    private HashMap<String, Objeto> objetos;
    private HashMap<String, Habitacion> salidas;
    private Enemigo enemigo;

}
