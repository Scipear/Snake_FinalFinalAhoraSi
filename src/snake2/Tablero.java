package snake2;

import java.util.ArrayList;

import controladores.Controlador_Host;
import controladores.Controlador_PreConeccion;
import ost.ReproductorSonidos;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete06Comida;
import snake2.Contenedor_Paquetes.Paquete10Effect;
import snake2.Contenedor_Paquetes.Paquete11Board;

/**
 * Clase parte que representa al tablero donde ocurre toda la partida
 * 
 * @version 1.0.1
 */
public class Tablero implements Comunicacion{
    private final int ancho = 22; //Ancho del mapa
    private final int alto = 22; //Alto del mapa
    private final int cantidadComidasEspeciales = 4; //Cantidad de comidas especiales disponibles en el juego
    private static boolean rapidezActivada; //Si el efecto de rapidez esta activado en el tablero
    private static ReproductorSonidos ostSFX = new ReproductorSonidos();
    private static String sfxOstRuta;
    private ArrayList<Personaje> personajes; //Los personajes que seran parte de la partida
    private Celda celdas[][]; //Matriz que representa al tablero
    private Comida comidaRegular; //La comida regular que aparece en el mapa
    private Comida comidaEspecial; //La comida especial que aparece en el mapa
    private Paquete06Comida food; //Paquete para informar a los clientes cuando se ha generado una nueva comida
    private Paquete10Effect effect; //Paquete para informar a los clientes que una fruta ha sido comida y se generara su efecto
    private Paquete11Board board; //Paquete para informar a los clientes sobre actualizaciones del estado del tablero
    private boolean pausa; //Si el juego esta o no en pausa
    private int mapa; //Tipo de mapa escogido por el jugador
    private int tiempoComidaEspecial; //Tiempo restante para que aparezca una comida especial
    
    /**
     * Constructor de la clase
     * 
     * @param jugadores Jugador o jugadores que van a jugar en el tablero
     * @param tipo Mapa elegido por el jugador
     */
    public Tablero(ArrayList<Jugador> jugadores, int tipo){
        celdas = new Celda[alto][ancho];
        mapa = tipo;
        pausa = false;  
        rapidezActivada = false;
        tiempoComidaEspecial = 60;
        personajes = new ArrayList<>();
        iniciarPersonajes(jugadores);
        inicializarTablero();
    }

    /**
     * Genera comida aleatoriamente en el tablero
     * 
     * @version 1.1
     */
    public void generarComida(){
        int posiciones[] = casillaAleatoria();
        comidaRegular = new Comida(posiciones[0], posiciones[1]);
        food = new Paquete06Comida(posiciones[0], posiciones[1], 4);
        enviarServidor(food);
    }

    /**
     * Genera un tipo de comida especial aleatoriamente en el tablero
     * 
     * @version 1.1.4
     */
    public void generarComidaEspecial(){
        int posiciones[] = casillaAleatoria();
        int indice = comidaAleatoria();

        switch(indice){
            case 0:{
                comidaEspecial = new ComidaDoble(posiciones[0], posiciones[1]);
                break;
            }

            case 1:{
                comidaEspecial = new ComidaPicante(posiciones[0], posiciones[1]);
                break;
            }

            case 2:{
                comidaEspecial = new ComidaCongelada(posiciones[0], posiciones[1]);
                break;
            }

            case 3:{
                comidaEspecial = new ComidaRocosa(posiciones[0], posiciones[1]);
                break;
            }
        }
        food = new Paquete06Comida(posiciones[0], posiciones[1], indice);
        enviarServidor(food);
        desactivarEfectos();
    }
    /**
     * Genera una posicion libre y aleatoria en el tablero
     * 
     * @return Devuelve un arreglo de posiciones (X, Y) aleatorias
     * @version 1.1.4
     */
    public int[] casillaAleatoria(){
        int posiciones[] = new int[2];

        do{
            posiciones[0] = (int) (Math.random() * ((ancho - 2) - 1) + 1) + 1;
            posiciones[1] = (int) (Math.random() * ((alto - 2) - 1) + 1) + 1;
        }while(!casillaLibre(posiciones[0], posiciones[1]));

        return posiciones;
    }

    /**
     * Confirma que una casilla este libre, no sea pared ni se encuentre un personaje o comida sobre ella
     * 
     * @param x Posicion en x de la casilla
     * @param y Posicion en y de la casilla
     * @return true si en la casilla indicada esta libre
     * @version 1.1
     */
    public boolean casillaLibre(int x, int y){
        Cuerpo cuerpo;

        for(int i = 0; i < personajes.size(); i++){
            for(int j = 0; j < personajes.get(i).getLongitud(); j++){
                cuerpo = personajes.get(i).getCuerpo(j);
                if(cuerpo.getPosX() == x && cuerpo.getPosY() == y){
                    return false;
                }
            }
        }

        if((hayComida() && comidaRegular.getPosX() == x && comidaRegular.getPosY() == y) || (hayComidaEspecial() && comidaEspecial.getPosX() == x && comidaEspecial.getPosY() == y)){
            return false;
        }

        if("Pared".equals(celdas[y][x].getTipo())){
            return false;
        }

        return true;
    }

    /**
     * Verifica si el personaje se encuentra sobre una comida
     * 
     * @param jugadores Jugadores que se encuentran en la partida
     * @version 1.1.1
     */
    public boolean personajeSobreComida(ArrayList<Jugador> jugadores){
        for(int i = 0; i < personajes.size(); i++){
            if(personajes.get(i).getCuerpo(0).getPosX() == comidaRegular.getPosX() && personajes.get(i).getCuerpo(0).getPosY() == comidaRegular.getPosY()){
                comidaRegular.hacerEfecto(personajes.get(i));
                effect = new Paquete10Effect(2, i, comidaRegular.getPosX(), comidaRegular.getPosY());
                enviarServidor(effect);
                comidaEstandarSFX();
                comidaRegular = null;
                food = new Paquete06Comida(0, 0, -2);
                enviarServidor(food);
                generarComida();
                jugadores.get(i).aumentaPuntaje();
                board = new Paquete11Board(i, 3);
                enviarServidor(board);
                return true;
    
            }else if(hayComidaEspecial() && personajes.get(i).getCuerpo(0).getPosX() == comidaEspecial.getPosX() && personajes.get(i).getCuerpo(0).getPosY() == comidaEspecial.getPosY()){
                comidaEspecial.hacerEfecto(personajes.get(i));
                effect = new Paquete10Effect(1, i, comidaEspecial.getPosX(), comidaEspecial.getPosY());
                enviarServidor(effect);
                comidaEspecialSFX();
                comidaEspecial = null;
                food = new Paquete06Comida(0, 0, -1);
                enviarServidor(food);
                tiempoComidaEspecial = 60;
                jugadores.get(i).aumentaPuntaje();
                board = new Paquete11Board(i, 3);
                enviarServidor(board);
                return true;
            }
        }
        return false;      
    }

    /**
     * Inicializa un tipo de mapa a eleccion del jugador
     * 
     * @param tipo Tipo de mapa que se va a inicializar
     * @version 1.1.4
     */
    public void inicializarTablero(){
        for(int i = 0; i < alto; i++){
            for(int j = 0; j < ancho; j++){ //Inicializa los valores de cada celda
                if(i == 0 || i == alto - 1 || j == 0 || j == ancho - 1){ //Si esta en el borde, es una pared
                    celdas[i][j] = new Celda("Pared");
                    
                }else{
                    celdas[i][j] = new Celda("Piso");
                    
                }
            }
        }

        switch(mapa){
            case 1:{
                for(int i = 1; i < alto-1; i++){
                    for(int j = 1; j < ancho-1; j++){
                        if(((i == (alto / 2) - 1 || i == alto / 2) && (j > 3 && j < 18)) || ((j == (ancho / 2) - 1 || j == ancho / 2) && (i > 3 && i < 18))){
                            celdas[i][j].setTipo("Pared");                            
                        }
                    }               
                }
                break;
            }

            case 2:{
                for(int i = 1; i < alto-1; i++){
                    for(int j = 1; j < ancho-1; j++){
                        if((i % 3 == 0 && j % 3 == 0)){
                            celdas[i][j].setTipo("Pared");
                        }
                    }
                }
                break;
            }

            case 3:{
                for(int i = 1; i < alto-1; i++){
                    for(int j = 1; j < ancho-1; j++){
                        if(i % 3 == 0 && (j > 3 && j < 18)){
                            celdas[i][j].setTipo("Pared");
                        }
                    }
                }
                break;
            }
        }
    }
    /**
     * Revisa y actualiza el estado de cada personaje que esta dentro del juego
     * 
     * @version 1.1.6
     */
    public void chequeaPersonajes(){
        for(int i = 0; i < personajes.size(); i++){
            personajes.get(i).movimiento();
            personajes.get(i).chocaConCuerpo();
            chocaConSerpiente(i);
            chocaConPared(i);
            //sacarPersonaje(i);
        }
    }
    /**
     * Actualiza el tiempo que falta para que aparezca una comida especial y el tiempo en el que aparece en pantalla
     * 
     * @version 1.1.6
     */
    public void conteoComidaEspecial(){
        if(tiempoComidaEspecial != 0){
            tiempoComidaEspecial--;

        }else{
            if(!hayComidaEspecial()){
                generarComidaEspecial();
            }
                        
            comidaEspecial.actualizaTiempo();

            if(comidaEspecial.getTiempoVisible() == 0){
                borraComidaEspecial();
                food = new Paquete06Comida(0, 0, -1);
                enviarServidor(food);
            }

        }
    }

    /**
     * Verifica si la serpiente ha chocado con alguna pared
     * 
     * @version 1.1.2
     */
    public void chocaConPared(int puesto){
        if("Pared".equals(celdas[personajes.get(puesto).getCuerpo(0).getPosY()][personajes.get(puesto).getCuerpo(0).getPosX()].getTipo())){
            personajes.get(puesto).setEstado(false);
            board = new Paquete11Board(puesto, 0);
            enviarServidor(board);
        }
    }

    /**
     * Verifica si una serpiente ha chocado con otras en el tablero
     * 
     * @param puesto Indice para identificar la serpiente a evaluar
     * @version 1.1.6
     */
    public void chocaConSerpiente(int puesto){
        for(int i = 0; i < personajes.size(); i++){
            if(i != puesto){
                for(int j = 0; j < personajes.get(i).getLongitud(); j++){
                    if(personajes.get(puesto).getCuerpo(0).getPosX() == personajes.get(i).getCuerpo(j).getPosX() && personajes.get(puesto).getCuerpo(0).getPosY() == personajes.get(i).getCuerpo(j).getPosY() && personajes.get(i).getEstado()){
                        personajes.get(puesto).setEstado(false);
                        board = new Paquete11Board(puesto, 0);
                        enviarServidor(board);
                    }
                }
            }
        }
    }

    /**
     * Elimina la comida especial del tablero
     * 
     * @version 1.1.4
     */
    public void borraComidaEspecial(){
        comidaEspecial = null;
        tiempoComidaEspecial = 60;
    }

    /**
     * Si el efecto de rapidez esta activo, entonces se desactiva y vuelve a la velocidad normal
     * 
     * @version 1.1.4
     */
    public void desactivaRapidez(){
        if(rapidezActivada){
            rapidezActivada = false;
            Game.setDelay(175);
        }
    }

    /**
     * Desactiva los efectos que esten puestos sobre el tablero o alguno de los personajes
     * 
     * @version 1.1.6
     */
    public void desactivarEfectos(){
        desactivaRapidez();
        for(int i = 0; i < personajes.size(); i++){
            personajes.get(i).descongelar();
            effect = new Paquete10Effect(-1, i, 0, 0);
            enviarServidor(effect);
        } 
    }

    /**
     * Guarda los personajes de los jugadores en el tablero
     * 
     * @param jugadores Los jugadores de quienes se va a tomar sus personajes
     * @version 1.2
     */
    public void iniciarPersonajes(ArrayList<Jugador> jugadores){
        for(int i = 0; i < jugadores.size(); i++){
            personajes.add(jugadores.get(i).getPersonaje());
        }
    }

    /**
     * Hace un conteo de cuantos personajes vivos estan en la partida
     * 
     * @version 1.2.3
     */
    public boolean personajesVivos(){
        for(int i = 0; i < personajes.size(); i++){
            if(personajes.get(i).getEstado()){
                return true;
            }
        }

        return false;
    }

    // public void sacarPersonaje(int indice){
    //     if(!personajes.get(indice).getEstado()){
    //         personajes.remove(indice);
    //     }
    // }

    /**
     * Actualiza la comida en caso de una partida en multijugador
     * 
     * @param x Posicion en x de la comida
     * @param y Posicion en y de la comida
     * @param tipo Identificador de la comida
     * @version 1.2
     */
    public void actualizarComida(int x, int y, int tipo){
        switch(tipo){
            case -2:
                comidaRegular = null;
                break;
            

            case -1:
                borraComidaEspecial();
                break;
            
            case 0:
                comidaEspecial = new ComidaDoble(x, y);
                break;
            

            case 1:
                comidaEspecial = new ComidaPicante(x, y);
                break;
            

            case 2:
                comidaEspecial = new ComidaCongelada(x, y);
                break;
            

            case 3:
                comidaEspecial = new ComidaRocosa(x, y);
                break;
            

            case 4:
                comidaRegular = new Comida(x, y);
                break;
            
        }
    }

    /**
     * En caso de una partida en multijugador, realiza un efecto 
     * sobre el jugador dependiendo de la comida que haya comida
     * 
     * @param x Posicion en x de la comida
     * @param y Posicion en y de la comida
     * @param sobre_Comida Tipo de comida
     * @param indice Numero para identificar al personaje que comio
     * @version 1.2.1
     */
    public void hacer_Efectos (int x, int y, int sobre_Comida, int indice){
        if(sobre_Comida == 1){
            comidaEspecial.hacerEfecto(personajes.get(indice));

        }else if(sobre_Comida == 2){
            comidaRegular.hacerEfecto(personajes.get(indice));
          
        }if(sobre_Comida == -1){
            desactivaRapidez();
            personajes.get(indice).descongelar();
        }
        
    }

    //Metodos encargados de reproducir el sonido de la comida cuando se come
    public static void comidaEstandarSFX() {
        sfxOstRuta = "\\src\\ost\\ComidaEstandar_SFX.mp3";
        ostSFX.reproducirSFX(sfxOstRuta);
        System.out.println("Comida estandar sonido");
    }

    public static void comidaEspecialSFX() {
        sfxOstRuta = "\\src\\ost\\ComidaDoble_SFX.mp3";
        ostSFX.reproducirSFX(sfxOstRuta);
        System.out.println("Comida estandar sonido");
    }

    /**
     * Decide que tipo de comida especial se va a generar
     * 
     * @return Un numero aleatorio que representa un tipo de comida especial
     */
    public int comidaAleatoria(){
        return (int) (Math.random() * ((cantidadComidasEspeciales - 1) + 1));
    }

    /**
     * Verifica si hay comida regular en el tablero
     * 
     * @return true si ya hay comida regular en el tablero
     * @version 1.1
     */
    public boolean hayComida(){
        return comidaRegular != null;
    }

    /**
     * Comprueba si hay comida especial en el tablero
     * 
     * @return true si hay algun tipo de comida especial en el tablero
     * @version 1.1.4
     */
    public boolean hayComidaEspecial(){
        return comidaEspecial != null;
    }

    public void setPausa(boolean pausa){
        this.pausa = pausa;
    }
    
    public static void setRapidez(boolean rapidez){
        rapidezActivada = rapidez;
    }

    public Personaje getPersonaje(int i){
        return personajes.get(i);
    }
    
    public int getAlto(){
        return alto;
    }

    public int getAncho(){
        return ancho;
    }
    
    public String getCelda(int fila, int columna){
        return celdas[fila][columna].getTipo();
    }

    public Comida getComidaEspecial(){
        return comidaEspecial;
    }

    public Comida getComida(){
        return comidaRegular;
    }

    public boolean getPausa(){
        return pausa;
    }

    public int getCantidadPersonajes(){
        return personajes.size();
    }

    public int getMapa() {
        return mapa;
    }

    @Override
    public void enviarServidor(Paquete paquete) {
        if(Controlador_Host.modo == 2){
            paquete.enviarData(Controlador_PreConeccion.server);
        }
    }   
}