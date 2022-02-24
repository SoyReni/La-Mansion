/**
 * Alumno: Sebastian Caballero.
 * Nombre de archivo: Bolsa.java
 * Introduccion a la Programacion II.
 * FIUNI 2019.
 */

import java.util.HashMap;
import java.util.Set;

/**
 * Esta es la clase bolsa, representa el contenedor en el que el jugador
 * contendra los objetos que desee juntar. 
 * 
 */
public class Bolsa {
    /**
     * Crea un objeto de tipo Bolsa
     */
    public Bolsa() {
        luces = new HashMap<String, Luz>(); //contendor para las luces especificamente
        objetos = new HashMap<String, Objeto>(); //contenedor para los objetos
    }

    /**
     * Método para saber si en la bolsa hay una luz o no
     * @return boolean devuelve true si el contenedor de luces no esta vacio
     */
    public boolean hayLuz() {
        return true != luces.isEmpty();
    }

    /**
     * Método para aislar una luz del contenedor de luces
     * @return Luz devuelve una Luz
     */
    public Luz getLuz() {
        Set keys = luces.keySet();
        if (keys.isEmpty()) {
            return null;
        } else {
            Object[] key = keys.toArray();
            return luces.get(key[0]);
        }
    }
    
    /**
     * Método para saber si en la bolsa hay una llave
     * @return devuelve true si hay una llave
     */
    public boolean hayLlave() {
        String llave = "llave";
        for (String key : objetos.keySet()) {
            if (key.contains(llave)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método para aislar una llave del contenedor 
     * @return Objeto devuelve un objeto que tenga la palabra llave en su key
     */
    public Objeto getLlave() {
        String llave = "llave";
        for (String key : objetos.keySet()) {
            if (key.contains(llave)) {
                return objetos.get(key);
            }
        }
        return null;
    }

    /**
     * Método para saber si la bolsa esta vacia
     * @return boolean devuelve false si la bolsa esta vacia
     */
    public boolean hayObjetos() {
        if (true == luces.isEmpty()) {
            if (true == objetos.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    
    /**
     * Metodo para aislar un objeto de la bolsa
     * @param objeto String que representa el objeto a aislar
     * @return Objeto devuelve el objeto a aislar
     */

    public Objeto getObjeto(String objeto) {
        if (true == objetos.containsKey(objeto)) {
            return objetos.get(objeto);
        } else {
            return null;
        }
    }

    /**
     * Metodo para objetener la lista de objetos en la bolsa
     * @return HashMap devuelve el HashMap que contiene los objetos de la bolsa
     */
    public HashMap getObjetos() {
        return objetos;
    }

    /**
     * Metodo para quitar un objeto de la bolsa
     * @param key String que equivale al key del objeto a quitar
     */
    public void quitarObjeto(String key) {
        if (true == luces.containsKey(key)) {
            luces.remove(key);
        } else {
            if (true == objetos.containsKey(key)) {
                objetos.remove(key);
            } else {
                System.out.println("No tienes ese ojeto en tu bolsa...");
            }
        }
    }

    /**
     * Método para dejar un objeto en la bolsa
     * @param key String que equivale al key del objeto a dejar
     * @param value String que equivale al value del objeto a dejar
     */
    public void dejarObjeto(String key, Objeto value) {
        if ("Luz".equals(value.getClass().getSimpleName())) {
            objetos.put(key, value);
            luces.put(key, (Luz) value);
        } else {
            objetos.put(key, value);
        }
    }
    
    /**
     * Metodo para separar un objeto de la bolsa que luego se elimina
     * @param objeto String que representa el objeto a separar
     * @return Objeto objeto a separar
     */
    public Objeto separarObjeto(String objeto) {
        for (String key : luces.keySet()) {
            if (key.equals(objeto)) {
                return luces.get(key);
            }
        }
        for (String key : objetos.keySet()) {
            if (key.equals(objeto)) {
                return objetos.get(key);
            }
        }
        return null;
    }

    private HashMap<String, Luz> luces;
    private HashMap<String, Objeto> objetos;
    private Class Arma;
    private Class Luz;
}
