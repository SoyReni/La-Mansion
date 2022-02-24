
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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
 * Esta clase fue modificada
 */
/**
 * Esta clase es la principal para la aplicacion . Un juego de basado en texto.
 *
 * Para jugarlo, se debe crear una instancia de esta clase y llamar el metodo
 * "jugar"
 *
 * Esta clase crea inicializa a todas las otras, aqui empieza todo: crea todos
 * los Cuartos, crea los parsers (objetos que interpretan texto) y comienza el
 * juego. Tambien evalua los comandos que devuelve el parser.
 */
class Juego {

    /**
     * Crea el juego e inicializa su mapa interno
     */
    public Juego() {
        crearCuartos(); //crea el entorno de juego
        parser = new Parser(); 
        jugador = new Jugador(100, 4); //crea el jugador
        habitacionActual = null; //contiene la direccion de la habitcion en la que te encuentras 
        habitacionAnterior = null; //contiene la direccion de la ultima habitacion que visitaste
    }

    /**
     * Metodo que retorna la habitacion en la que el jugador se encuentra
     * @return Habitacion habitacion actual
     */
    public Habitacion getHabitacionActual() {
        return habitacionActual;
    }

    /**
     * Metodo para definir una nueva habitacion actual
     * @param siguiente Habitacion siguiente habitacion
     */
    public void setHabitacionActual(Habitacion siguiente) {
        habitacionAnterior = habitacionActual;
        habitacionActual = siguiente;
    }

    /**
     * Metodo que retorna la ultima habitacion que visitaste
     * @return Habitacion habitacion anterior
     */
    public Habitacion getHabitacionAnterior() {
        return habitacionAnterior;
    }

    /**
     * Metodo para definir la ultima habitacion visitada
     * @param habitacion Haabitacion habitacion anterior
     */
    public void setHabitacionAnterior(Habitacion habitacion) {
        habitacionAnterior = habitacion;
    }
    
    /**
     * Metodo para definir la habitacion de inicio, este metodo existe porque
     * al iniciar el juego no hay habitacion anterior
     * @param habitacion Habitacion habitacion de inicio
     */
    public void setHabitacionInicio(Habitacion habitacion) {
        habitacionActual = habitacion;
    }
    
    /**
     * Metodo que retorna el jugador del juego
     * @return Jugador jugador del juego
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Rutina principal: jugar. Itera hasta el fin del juego..
     * metodo proporcionado por el docente
     */
    public void jugar() {
        crearCuartos();
        imprimirBienvenida();

        // Jugar hasta que un comando me diga que ya no quiere jugar mas
        boolean continuar = true;
        while (continuar) {
            Comando comando = parser.getComando();
            continuar = comando.ejecutar(this);
        }
        System.out.println(CABEZA_PIE);
        System.out.println("Cobarde, nos volveremos a encontrar...");
        System.out.println(CABEZA_PIE);
    }

    /**
     * Intenta ir en una direccion. Si esta fue una salida, entra a otra
     * habitacion, en caso contrario imprime un mensaje de error.
     */
    public void irA(String direccion) {
        if (null == direccion) {
            // si no hay direccion no sabemos a donde ir
            System.out.println("¿Ir donde?");
            return;
        }
        // Intenta salir del cuarto
        Habitacion siguienteHabitacion = habitacionActual.siguienteHabitacion(direccion);
        if (null == siguienteHabitacion) {
            //si le indicamos una direecion no valida solo dice que no puede ir ahi
            System.out.println(
                    "No...");
        } else {
            if (true == habitacionActual.getSalida(direccion).estaCerrado()) {
                //si la puerta esta cerrada, pide una llave
                System.out.println(CABEZA_PIE);
                System.out.println("Esa puerta esta cerrada, necesitas una llave");
                verComandosUsuales();
            }
            if (false == habitacionActual.getSalida(direccion).estaCerrado()) {
                //si esta abierta, va. 
                System.out.println(CABEZA_PIE);
                setHabitacionAnterior(habitacionActual);
                setHabitacionActual(siguienteHabitacion);
                verComandosUsuales();
            }
        }
    }

    /**
     * Metodo para guardar un objeto en la bolsa del jugador
     * @param objeto String que representa el objeto a guardar
     */
    public void guardar(String objeto) {
        // intenta guardar un objeto
        if (null == objeto) {
            System.out.println("Guardar que?");
        } else {
            if (null == habitacionActual.getObjetos().get(objeto)) {
                System.out.println("Ese objeto no esta aqui!");
            } else {
                //no puede guardar un objeto si no hay espacio en la bolsa
                if ((jugador.getPesoAcumulado() + habitacionActual.separarObjeto(objeto).getPeso()) >= jugador.getFuerza()) {
                    System.out.println("No hay espacio suficiente en tu bolsa");                   
                } else {
                    //guarda el objeto
                    jugador.setPesoAcumulado(jugador.getPesoAcumulado() + habitacionActual.separarObjeto(objeto).getPeso());
                    jugador.getBolsa().dejarObjeto(objeto, habitacionActual.separarObjeto(objeto));
                    habitacionActual.quitarObjeto(objeto);
                    System.out.println("El objeto ha sido guardado con exito");
                    
                    System.out.println(jugador.mostrarContenidoBolsa());
                }
            }
        }
    }

    /**
     * Metodo para dejar un objeto de la bolsa del jugador en la habitacion
     * @param objeto String que representa el objeto a dejar
     */
    public void dejar(String objeto) {
        //intenta dejar un objeto
        if (objeto == null) {
            System.out.println("Dejar que?");
        } else {
            //no puede dejar un objeto que no tienes
            if (null == jugador.getBolsa().separarObjeto(objeto)) {
                System.out.println("No tienes ese objeto en tu inventario...");
            } else {
                //no se pueden dejar objetos en el ascensor
                if ("ascensor".equals(habitacionActual.getNombre())) {
                    System.out.println("No lo dejes aqui... es peligroso...");
                } else {
                    //deja el objeto
                    jugador.setPesoAcumulado(jugador.getPesoAcumulado() - jugador.getBolsa().separarObjeto(objeto).getPeso());
                    jugador.getBolsa().quitarObjeto(objeto);
                    habitacionActual.dejarObjeto(objeto, jugador.getBolsa().separarObjeto(objeto));
                    System.out.println("El objeto ya no se encuentra en tu bolsa");
                }
            }
        }
    }

    /**
     * Metodo para lanzar un objeto a algun enemigo
     * @param objeto String que representa el objeto a lanzar
     * @param enemigo String que representa el enemigo a atacar
     */
    public void lanzar(String objeto, String enemigo) {
        //intenta lanzar algo
        if (false == habitacionActual.hayEnemigo()) {
            //no puedes lanzar nada si no hay enemigo
            System.out.println("No hay nadie a quien atacar... estas viendo cosas...");
        } else {
            //no puedes lanzar nada si no tienes nada
            if (true == jugador.getBolsa().getObjetos().isEmpty()) {
                System.out.println("No tienes nada que lanzar...");
            } else {
                //no puedes lanzar algo que no tienes
                if (null == jugador.getBolsa().getObjeto(objeto)) {
                    System.out.println("No tienes ese objeto...");
                } else {
                    if (null == objeto) {
                        System.out.println("Lanzar que?");
                    } else {
                        if (null == enemigo) {
                            System.out.println("Lanzar " + objeto + " a quien?");
                        } else {
                            //no puedes atacar a un enemigo muerto
                            if (false == habitacionActual.getEnemigo().getEstado()) {
                                System.out.println("El enemigo esta muerto...");
                            } else {
                                //lanza el objeto al enemigo
                                habitacionActual.getEnemigo().setVida(habitacionActual.getEnemigo().getVida() - jugador.getBolsa().getObjeto(objeto).getDanio());
                                habitacionActual.getEnemigo().setEstado();
                                //muestra en pantalla la vida del enemigo
                                System.out.println(habitacionActual.getEnemigo().getNombre() + ": " + habitacionActual.getEnemigo().getVida() + " HP");
                                //el objeto que lanzaste sale de tu inventario
                                jugador.setPesoAcumulado(jugador.getPesoAcumulado() - jugador.getBolsa().getObjeto(objeto).getPeso());
                                jugador.getBolsa().quitarObjeto(objeto);
                                //el enmigo te ataca
                                jugador.setVida(jugador.getVida() - habitacionActual.getEnemigo().getAtaque());
                                jugador.setEstado();
                                //muestra en pantalla tu vida
                                System.out.println("Tu: " + jugador.getVida() + " HP.");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo para volver a la habitacion de la que viniste
     */
    public void atras() {
        if (null != habitacionAnterior) {
            Habitacion habitacionGuardada = habitacionActual;
            setHabitacionActual(habitacionAnterior);
            setHabitacionAnterior(habitacionGuardada);
            verComandosUsuales();
        } else {
            System.out.println("No hay donde volver si no vienes de ningun lado...");
        }
    }

    /**
     * Metodo para abrir una habitacion 
     * @param habitacion String que representa la habitacion a abrir
     * @param llave String que representa la llave para abir la puerta
     */
    public void abrir(String habitacion, String llave) {
        // intenta abrir una puerta
        if (null == habitacion) {
            System.out.println("Abrir que?");
        } else {
            if (null == habitacionActual.getSalida(habitacion)) {
                System.out.println("No puedo abrir eso...");
            } else {
                if (false == habitacionActual.getSalida(habitacion).estaCerrado()) {
                    System.out.println("Esa puerta esta abierta");
                } else {
                    if (null == llave) {
                        System.out.println("Abrir " + habitacion + " con que?");
                    } else {
                        //no puedes abrir una puerta si no tienes una llave en el inventario
                        if (false == jugador.getBolsa().hayLlave()) {
                            System.out.println("No tienes nada para abrir esta puerta...");
                        } else {
                            if (true == jugador.getBolsa().hayLlave()) {
                                habitacionActual.getSalida(habitacion).abrir();
                                jugador.getBolsa().quitarObjeto(jugador.getBolsa().getLlave().getNombre());
                                System.out.println("Has abierto esta puerta...");
                            }
                        }

                    }
                }
            }
        }
    }
    
     /**
     * Metodo para forzar el cierre del juego bajo ciertas condiciones
     * @return boolean false para terminar el juego, true para continuar
     */
    public boolean terminar() {
        if (habitacionActual.getNombre().equals("arena")) {
            //si la habitacion actual es la arena y el enemigo esta muerto
            //termina el juego y ganas la partida
            if (true == jugador.getEstado()) {
                if (true == habitacionActual.getEnemigo().getEstado()) {
                    return true;
                } else {
                    imprimirMensajeVictoria();
                    return false;
                }
            }
        }
        //si estas muerto, termina el juego y pierdes la partida
        if (false == jugador.getEstado()) {
            System.out.println("Has perdido...");
            return false;
        }
        return true;
    }

    /**
     * Crea todas las habitaciones y enlaza todas sus salidas, sus objetos y
     * enemigos si es que los tiene
     */
    private void crearCuartos() {

        // crear los cuartos
        Habitacion lobby = new Habitacion("lobby", "el lobby de la mansión", false);
        Habitacion biblioteca = new Habitacion("biblioteca", "una biblioteca... con pocos libros", true);
        Habitacion comedor = new Habitacion("comedor", "un viejo comedor", false);
        Habitacion cocina = new Habitacion("cocina", "lo que alguna vez fue una cocina moderna", true);
        Habitacion cuarto = new Habitacion("cuarto", "un cuarto vacio", true);
        Habitacion sanitario = new Habitacion("baño", "un cuarto de baño en mal estado", true);
        Habitacion oficina = new Habitacion("oficina", "unas oficinas... con computadoras?", true);
        Habitacion arena = new Habitacion("arena", "un gran cuarto... que es eso en el medio?", true);
        Habitacion pasillo1 = new Habitacion("pasillo1", "un pasillo oscuro... no se ve donde termina", false);
        Habitacion lavado = new Habitacion("lavado", "un cuarto de lavado sucio", true);
        Habitacion pasillo2 = new Habitacion("pasillo2", "un pasillo oscuro... no se ve donde termina", false);
        Habitacion caldera = new Habitacion("caldera", "el cuarto de la calefaccion central de la casa, parece que no funciona", false);
        Habitacion pasillo3 = new Habitacion("pasillo3", "un pasillo oscuro, no se ve donde termina", false);
        Habitacion alacena = new Habitacion("alacena", "un cuarto de almacenaje vacio", true);
        Habitacion pasillo4 = new Habitacion("pasillo4", "un pasillo oscuro, hay una puerta al final", false);
        Habitacion arenita = new Habitacion("habitacion", "una habitacion pequeña", true);
        Habitacion ascensor = new Habitacion("ascensor", "un ascensor?", false);

        // inicializar las salidas
        lobby.setSalidas(biblioteca, comedor, cocina, cuarto, ascensor);
        biblioteca.setSalidas(lobby, null, null, null, null);
        comedor.setSalidas(cocina, lobby, null, null, null);
        cocina.setSalidas(comedor, lobby, null, null, null);
        cuarto.setSalidas(sanitario, lobby, null, null, null);
        sanitario.setSalidas(cuarto, null, null, null, null);
        oficina.setSalidas(arena, ascensor, null, null, null);
        arena.setSalidas(oficina, null, null, null, null);
        pasillo1.setSalidas(lavado, ascensor, pasillo2, null, null);
        lavado.setSalidas(pasillo1, null, null, null, null);
        pasillo2.setSalidas(caldera, pasillo1, pasillo3, null, null);
        caldera.setSalidas(pasillo2, null, null, null, null);
        pasillo3.setSalidas(alacena, pasillo3, pasillo4, null, null);
        alacena.setSalidas(pasillo3, null, null, null, null);
        pasillo4.setSalidas(arenita, pasillo3, null, null, null);
        arenita.setSalidas(pasillo4, null, null, null, null);
        ascensor.setSalidas(lobby, oficina, pasillo1, null, null);

        //Crear los objetos
        Luz lampara = new Luz("lampara", 0.5, 18, 10, true);
        Objeto alfombra = new Objeto("alfombra", 0.6, 10);
        Objeto florero = new Objeto("florero", 0.6, 13);
        Objeto llave1 = new Objeto("llaveAzul", 0.1, 10);
        Objeto libreta = new Objeto("libreta", 0.2, 12);
        Objeto libro = new Objeto("libro", 0.7, 13);
        Objeto candelabro = new Objeto("candelabro", 1, 23);
        Objeto tintero = new Objeto("tintero", 0.3, 10);
        Objeto llave2 = new Objeto("llaveRoja", 0.1, 10);
        Objeto perfume = new Objeto("perfume", 0.3, 14);
        Objeto destornillador = new Objeto("destornillador", 0.3, 20);
        Objeto zapato = new Objeto("zapato", 0.5, 13);
        Objeto tela = new Objeto("tela", 0.1, 9);
        Objeto jabon = new Objeto("jabon", 0.1, 20);
        Objeto espejo = new Objeto("espejo", 0.4, 15);
        Objeto llave3 = new Objeto("llaveAmarilla", 0.1, 10);
        Objeto cuchillo = new Objeto("cuchillo", 0.1, 15);
        Objeto taza = new Objeto("taza", 0.2, 11);
        Objeto tenedor = new Objeto("tenedor", 0.2, 8);
        Objeto llave4 = new Objeto("llaveVerde", 0.1, 10);
        Objeto sal = new Objeto("sal", 0.2, 25);
        Objeto caramelo = new Objeto("caramelo", 0.05, 5);
        Objeto grifo = new Objeto("grifo", 0.3, 12);
        Luz fosforo = new Luz("fosforo", 0.01, 0, 5, true);
        Objeto llave5 = new Objeto("llaveNegra", 0.1, 10);
        Objeto cadenas = new Objeto("cadenas", 0.7, 17);
        Objeto frasco = new Objeto("frasco", 0.5, 17);
        Objeto llave6 = new Objeto("llaveBlanca", 0.1, 10);
        Objeto salero = new Objeto("salero", 0.2, 30);
        Objeto piedra = new Objeto("piedra", 0.8, 19);
        Objeto palanca = new Objeto("palanca", 0.8, 20);
        Objeto llave7 = new Objeto("llaveMorada", 0.1, 10);
        Luz linterna = new Luz("linterna", 0.3, 12, 20, true);
        Objeto lata = new Objeto("lata", 0.2, 8);
        Objeto escoba = new Objeto("escoba", 0.5, 16);
        Objeto agua = new Objeto("agua", 0.2, 5);
        Objeto pluma = new Objeto("pluma", 0, 20);
        Objeto llave8 = new Objeto("llaveNaranja", 0.1, 10);
        Objeto martillo = new Objeto("martillo", 0.9, 25);
        Objeto llave9 = new Objeto("llaveMarron", 0.1, 10);
        
        //Inicializar los objetos
        lobby.setObjetos(lampara, alfombra, florero, llave1, null, null);
        biblioteca.setObjetos(libreta, candelabro, libro, tintero, null, null);
        cuarto.setObjetos(llave2, perfume, destornillador, zapato, null, null);
        sanitario.setObjetos(tela, jabon, espejo, llave3, null, null);
        comedor.setObjetos(cuchillo, llave4, taza, tenedor, null, null);
        cocina.setObjetos(sal, caramelo, grifo, null, null, null);
        pasillo1.setObjetos(fosforo, llave5, null, null, null, null);
        lavado.setObjetos(frasco, cadenas, llave6, null, null, null);
        caldera.setObjetos(salero, piedra, palanca, llave7, null, null);
        alacena.setObjetos(linterna, lata, escoba, null, null, null);
        arenita.setObjetos(agua, pluma, llave8, martillo, null, null);
        oficina.setObjetos(llave9, null, null, null, null, null);

        //Crear los enemigos
        Enemigo harrin = new Enemigo("Harrin", 100, 15);
        Enemigo john = new Enemigo("John", 80, 8);
        Enemigo rata = new Enemigo("Rata", 30, 5);
        //Inicializar los enemigos
        arena.setEnemigo(harrin);
        lavado.setEnemigo(rata);
        arenita.setEnemigo(john);

        // empezar juego en el lobby
        setHabitacionActual(lobby);
    }

    /**
     * Metodo que muestra en pantalla informacion recurrente
     */
    private void verComandosUsuales() {
        //se muestra la habitacion en la que te encuentras
        System.out.printf(FORMATO2, habitacionActual.getDescripcion());
        //si no tienes una luz, no puedes ver los objetos
        if (false == jugador.getBolsa().hayLuz()) {
            System.out.printf(FORMATO2,"No puedo ver lo que hay aqui, no tienes una fuente de luz...");
        } else {
            //si tienes una luz pero ya no funciona, no puedes ver los objetos
            if (0 >= jugador.getBolsa().getLuz().getIluminacion()) {
                System.out.printf(FORMATO2,"No puedo ver lo que hay aqui, la luz que tienes ya no funciona...");
            } else {
                jugador.getBolsa().getLuz().setIluminacion();
                if (habitacionActual.hayEnemigo() == true) {
                    //si hay un enemigo en la habitacion no puedes ver los objetos
                    System.out.printf(FORMATO2,"Hay un enemigo en la habitacion, no puedo ver lo que hay dentro");
                    System.out.printf(FORMATO2,habitacionActual.textoEnemigos());
                } else {
                    //muestra los objetos de la habitacion
                    System.out.printf(FORMATO2,habitacionActual.textoObjetos());
                }
            }
        }
        //se muestra el contenido de la bolsa del jugador
        System.out.println(jugador.mostrarContenidoBolsa());
        System.out.println(CABEZA_PIE);
        //se muestran los comandos disponibles
        System.out.printf(FORMATO2, mostrarComandos());
    }

    /**
     * Metodo que retorna un String con los comandos disponibles
     * @return String comandos disponibles
     */
    private String mostrarComandos() {
        FabricaDeComandos fabrica = new FabricaDeComandos();
        Collection listaComandos = fabrica.comandosConocidos();
        String comandos = "";
        for (Iterator iter = listaComandos.iterator(); iter.hasNext();) {
            comandos += " " + ((String) iter.next());
        }
        return "Comandos: " + comandos;
    }

    /**
     * Imprime el mensaje de bienvenida del jugador..
     */
    private void imprimirBienvenida() {
        System.out.println(CABEZA_PIE);
        System.out.printf(FORMATO1, "\t          La Mansion...");
        imprimirMansion();
        imprimirHistoriaInicial();
        System.out.printf(FORMATO2, habitacionActual.getDescripcion());
        System.out.printf(FORMATO2, habitacionActual.textoObjetos());
        System.out.printf(FORMATO2, mostrarComandos());
        System.out.println(CABEZA_PIE);
    }

    /**
     * Metodo que imprime un dibujo de tipo ascii art en pantalla
     * recuperado de https://www.asciiart.eu/buildings-and-places/castles
     */
    private void imprimirMansion() {
        System.out.println(CABEZA_PIE);
        System.out.printf(FORMATO1, "\t                  ^");
        System.out.printf(FORMATO1, "\t                 / \\");
        System.out.printf(FORMATO1, "\t            ^   _|.|_   ^");
        System.out.printf(FORMATO1, "\t          _|I|  |I .|  |.|_");
        System.out.printf(FORMATO1, "\t          \\II||~~| |~~||  /");
        System.out.printf(FORMATO1, "\t           ~\\~|~~~~~~~|~/~");
        System.out.printf(FORMATO1, "\t             \\|II I ..|/");
        System.out.printf(FORMATO1, "\t        /\\    |II.    |    /\\");
        System.out.printf(FORMATO1, "\t       /  \\  _|III .  |_  /  \\");
        System.out.printf(FORMATO1, "\t       |-~| /(|I.I I  |)\\ |~-|");
        System.out.printf(FORMATO1, "\t     _/(I | +-----------+ |. )\\_");
        System.out.printf(FORMATO1, "\t     \\~-----/____-~-____\\-----~/");
        System.out.printf(FORMATO1, "\t      |I.III|  /(===)\\  |  .. |");
        System.out.printf(FORMATO1, "\t      /~~~-----_________---~~~\\.");
        System.out.printf(FORMATO1, "\t     `8888888888!\\-888888!!!!!| |\\.");
        System.out.printf(FORMATO1, "\t    _/88888888888!!\\~~-_888!!!\\_/|");
        System.out.printf(FORMATO1, "\t    \\88888888888888!!!!!/~~-_8!!!!\\.");
        System.out.printf(FORMATO1, "\t     ~|88888888888888888!!!!!/~~--\\_");
        System.out.printf(FORMATO1, "\t  __ /88888888888888888888888!!!!/ /");
        System.out.printf(FORMATO1, "\t  \\,~\\-_____88888888888888888!!!!\\/");
        System.out.printf(FORMATO1, "\t  /!!!!\\ \\ \\~-_88888888888888!!!!\\.");
        System.out.printf(FORMATO1, "\t /88888!!!!!!!\\~-_8888888888!!!!!!\\_");
        System.out.printf(FORMATO1, "\t/8888888888888!!!\\888888888888!!!!!!\\.");
        System.out.println(CABEZA_PIE);
    }

    /**
     * Metodo que imprime en pantalla la historia que le da inicio al juego
     */
    private void imprimirHistoriaInicial() {
        System.out.printf(FORMATO2, "Te despiertas sin saber lo que ha pasado, no hay luz, solo se puede ver la luz de la luna");
        System.out.printf(FORMATO2, "que se asoma por las pocas ventanas que estan abarrotadas en el exterior... Tambien hay");
        System.out.printf(FORMATO2, "una gran puerta de madera que da al exterior, intentas abrirla pero no tienes exito...");
        System.out.printf(FORMATO2, "parece que necesitas una llave...");
        System.out.printf(FORMATO2, "Lo que logras ver son simples muebles llenos de polvo, algunos con cajones entreabiertos");
        System.out.printf(FORMATO2, "como si alguien se hubiera llevado todas sus pertenencias apresuradamente.");
        System.out.printf(FORMATO2, "No encuentras explicacion de porque estas aqui, quien te llevo hasta la mansion?");
        System.out.printf(FORMATO2, "El lugar donde te despertaste es un amplio lobby de una gran mansion con algunas puertas");
        System.out.printf(FORMATO2, "deberias explorarlas... Tambien tienes una lampara frente a ti, te podria ser útil...");
        System.out.printf(FORMATO2, "");
        System.out.printf(FORMATO2, "Antes de que puedas dar tu primer paso... escuchas una voz...");
        System.out.printf(FORMATO2, "");
        System.out.printf(FORMATO2, "                ...CUENTO TUS PASOS...UNO...DOS");
        System.out.printf(FORMATO2, "");
        System.out.printf(FORMATO2, "               ...CUANTOS DARAS ANTES DE MORIR?...");
        System.out.printf(FORMATO2, "");
        System.out.printf(FORMATO2, "Objetivo: encuentra y vence al que te llevo a la mansion. Antes de que el venga por ti...");
        System.out.printf(FORMATO2, "encuentra la llave dorada y sal de aqui...");
        System.out.printf(FORMATO2, "Escribe ayuda para mas informacion");
        System.out.println(CABEZA_PIE);
    }
    
     /**
     * Metodo que imprime en pantalla la historia que le da inicio al juego
     */
    private void imprimirMensajeVictoria() {
        System.out.println(CABEZA_PIE);
        System.out.printf(FORMATO2, "Derrotaste a Harrin... no lo puedes creer...");
        System.out.printf(FORMATO2, "Detras de su cuerpo inerte ves una gran llave dorada... no dudas en tomarla");
        System.out.printf(FORMATO2, "A un lado de la llave hay unas hojas impresas que dicen..");
        System.out.printf(FORMATO2, "");
        System.out.printf(FORMATO2, "        _________________________________________________________________");
        System.out.printf(FORMATO2, "       |                                                                 |");
        System.out.printf(FORMATO2, "       |Mayo de 2007                                                     |");
        System.out.printf(FORMATO2, "       |EXPERIMENTO Nº 73                                                |");
        System.out.printf(FORMATO2, "       |FALLIDO.                                                         |");
        System.out.printf(FORMATO2, "       |El sujeto del experimeto John Peterson de 41 años ha enloquecido |");
        System.out.printf(FORMATO2, "       |luego de 18 horas de exposicion. El doctor Harrin esta empezando |");
        System.out.printf(FORMATO2, "       |a actuar de manera erratica.                                     |");
        System.out.printf(FORMATO2, "       |Esto se nos fue de las manos, es el fin.                         |");
        System.out.printf(FORMATO2, "       |                                                                 |");
        System.out.printf(FORMATO2, "       |                                           Dra. Lee Harper       |");
        System.out.printf(FORMATO2, "       |_________________________________________________________________|");
        System.out.printf(FORMATO2, "");
        System.out.printf(FORMATO2, "No quieres saber nada mas, esto fue suficiente...");
        System.out.printf(FORMATO2, "Te apresuras, vas al lobby de la mansion y corres a la puerta de salida ");
        System.out.printf(FORMATO2, "la cerradura no cede... la puerta no se abre");
        System.out.printf(FORMATO2, "escuchas pasos detras de ti...");
        System.out.printf(FORMATO2, "...");
        System.out.printf(FORMATO2, "Has ganado, gracias por jugar.");
        System.out.println(CABEZA_PIE);
    }

    private Jugador jugador;
    private HashMap<String, Habitacion> habitaciones;
    private Parser parser;
    private Habitacion habitacionActual;
    private Habitacion habitacionAnterior;

    //Formato de los textos en pantalla
    private final String FORMATO1 = "| %-85s|\n";
    private final String FORMATO2 = "| %-90s|\n";
    private final String CABEZA_PIE = "=============================================================================================";
}
