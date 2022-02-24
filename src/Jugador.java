/**
 * Alumno: Sebastian Caballero. 
 * Nombre de archivo: Jugador.java 
 * Introduccion a la Programacion II. 
 * FIUNI 2019.
 */

import java.util.Iterator;
import java.util.Set;

/**
 * esta clase representa al jugador dentro del juego, el jugador se ubica
 * en una habitacion y tiene una bolsa para contener los objetos que lleva.
 * @author Sebastian Caballero
 */
public class Jugador {
    /**
     * Crea un objeto de tipo jugador
     * @param vida int vida del jugador
     * @param fuerza double cantidad de peso que puede llevar el jugador
     */
    public Jugador(int vida, double fuerza) {
        _Estado = true; //variable que indica si el jugador esta vivo o no
        _Vida = vida;
        _Fuerza = fuerza;
        pesoAcumulado = 0; //peso acumulado en la bolsa
        bolsa = new Bolsa(); //bolsa que contiene los objetos que lleva
    }

    /**
     * Metodo que retorna la vida del jugador
     * @return int vida del jugador
     */
    public int getVida() {
        return _Vida;
    }

    /**
     * Metodo que retorna la cantidad de peso que puede llevar el jugador
     * @return souble fuerza del jugador
     */
    public double getFuerza() {
        return _Fuerza;
    }

    /**
     * Metodo que retorna el contenedor de los objetos del jugador
     * @return Bolsa bolsa del jugador
     */
    public Bolsa getBolsa() {
        return bolsa;
    }
    
    /**
     * Metodo que retorna el peso acumulado en la bolsa del jugador
     * @return double peso acumulado
     */
    public double getPesoAcumulado() {
        return pesoAcumulado;
    }
    
    /**
     * Metodo para saber si el jugador sigue vivo
     * @return boolean true si esta vivo, false si esta muerto
     */
    public boolean getEstado() {
        return _Estado;
    }
    
    /**
     * Metodo para modificar el peso acumulado
     * @param pesoNuevo double nuevo peso acumulado
     */
    public void setPesoAcumulado(double pesoNuevo) {
        pesoAcumulado = pesoNuevo;
    }

    /**
     * Metodo para modificar el valor de la vida del jugador
     * @param vidaNueva int nuevo valor de la vida del jugador
     */
    public void setVida(int vidaNueva) {
        _Vida = vidaNueva;
    }

    /**
     * Metodo para modificar el estado del jugador
     */
    public void setEstado() {
        //si la vida es menor o igual que 0, esta muerto
        if (0 >= _Vida) {
            _Estado = false;
        // en otro caso, esta vivo    
        } else {
            _Estado = true;
        }
    }
      
    /**
     * Metodo para ver el contenido de la bolsa del jugador en pantalla
     * @return String contenido de la bolsa del jugador
     */
    public String mostrarContenidoBolsa() {
        String objetos = "Inventario:";
        Set keys = bolsa.getObjetos().keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            objetos += " " + iter.next();
        }
        return objetos;
    }
    
    private boolean _Estado;
    private int _Vida;
    private double _Fuerza;
    private double pesoAcumulado;
    private Bolsa bolsa;
    private Habitacion habitacionActual;
    private Habitacion habitacionAnterior;
}
