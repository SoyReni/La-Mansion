/*
 * Universidad Nacional de Itapua.
 * Proyecto Zork.
 *
 * Autor Original: Michael Kolling, Universidad de Monash
 * Version: 1.1
 * Date: March 2000
 * Copyright (c) Michael Kolling
 *
 * Nombre del Alumno: Sebastian Caballero
 * Esta clase no fue modificada
 */

/**
 * Este comando es utilizado cuando el sistema no reconoce el comando pedido
 * por el usuario. Emite un mensaje para decirle al usuario sus errores.
 *
 */
public class ComandoDesconocido extends ComandoAbstracto {

    /**
     * Imprime un mensaje de error
     */

    @Override
    public boolean ejecutar(Juego juego) {
        String comandoNoReconocido = (String) getPalabras().get(0);
        if (null != comandoNoReconocido) {
            System.out.println("No entiendo que quieres decir con \"" +
                           comandoNoReconocido + "\".. habla claro");
        } else {
            System.out.println("No entiendo lo que me estas diciendo..");
        }
        return true;
    }

}
