/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: Enemigo.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */

/**
 * Esta clase representa a los enemigos del juego
 * @author Sebastian Caballero
 */
public class Enemigo {
    /**
     * Crea un nuevo objeto enemigo
     * @param nombre String nombre del enemigo
     * @param vida int vida del enemigo
     * @param ataque inr ataque del enemigo
     */
    public Enemigo (String nombre, int vida, int ataque){
        _Vivo = true; //parametro que determina si el enemigo esta vivo o muerto
        _Nombre = nombre; 
        _Vida = vida;
        _Ataque = ataque; 
    }

    /**
     * Metodo para determinar si el enemigo esta vivo o muerto
     */
    public void setEstado (){
        //si la vida es 0 o menos, el enemigo esta muerto
        if (0 >= this.getVida()){
            _Vivo = false; 
        }
    }
    
    /**
     * Metodo que retorna el estado de un enemigo
     * @return boolean devuelve true si el enemigo esta vivo
     */
    public boolean getEstado () {
        return _Vivo;
    }
    
    /**
     * Metodo que retorna el nombre del enemigo
     * @return String devuelve el nombre del enemigo
     */
    public String getNombre (){
        return _Nombre;
    }
    
    /**
     * Metodo para cambiar el valor de la vida del enemigo
     * @param nuevaVida int nuevo valor de la vida del enemigo
     */    
    public void setVida (int nuevaVida) {
        this._Vida = nuevaVida;
    } 
    
    /**
     * Metodo que retorna la vida del enemigo
     * @return int devuelve la vida del enemigo
     */
    public int getVida (){
        return _Vida; 
    } 
    
    /**
     * Metodo que retorna el valor del ataque del enemigo.
     * @return int devuelve el ataque del enemigo
     */
    public int getAtaque (){
        return _Ataque; 
    }
    
    private boolean _Vivo;
    private String _Nombre; 
    private int _Vida; 
    private int _Ataque; 
}
