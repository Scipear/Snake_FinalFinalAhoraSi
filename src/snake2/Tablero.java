package snake2;

import java.util.ArrayList;

import controladores.HostLobbie_Controlador;
import controladores.PreConeccion_Controlador;
import ost.ReproductorSonidos;
import snake2.Contenedor_Paquetes.Paquete05Update;
import snake2.Contenedor_Paquetes.Paquete06Comida;

/**
 * Clase parte del back. Donde ocurre toda la partida
 * 
 * @version 1.0.1
 */
public class Tablero {
    private final int ancho;
    private final int alto;
    private final int cantidadComidasEspeciales;
    private static boolean rapidezActivada;
    private static ReproductorSonidos ostSFX = new ReproductorSonidos();
    private static String sfxOstRuta;
    private Paquete06Comida food;
    private Celda celdas[][];
    private Comida comidaRegular;
    private Comida comidaEspecial;
    private ArrayList<Personaje> personajes;
    private int tiempoComidaEspecial;
    private boolean pausa;
    private int mapa;
    
    /**
     * Constructor de la clase
     * 
     * @param jugador Persona, jugador o usuario que va a jugar en el tablero
     */
    public Tablero(ArrayList<Jugador> jugadores, int tipo){
        ancho = 22;
        alto = 22;
        cantidadComidasEspeciales = 4;
        celdas = new Celda[alto][ancho];
        mapa = tipo;
        pausa = false;  
        rapidezActivada = false;
        tiempoComidaEspecial = 60;
        personajes = new ArrayList<>();
        iniciarPersonajes(jugadores);
        inicializarTablero(tipo);
    }

    /**
     * Genera comida aleatoriamente en el tablero
     * 
     * @version 1.1
     */
    public void generarComida(){
        int posiciones[] = casillaAleatoria();
        comidaRegular = new Comida(posiciones[0], posiciones[1]);
        
        if(PreConeccion_Controlador.server != null){
            food = new Paquete06Comida(posiciones[0], posiciones[1], 4);
            food.enviarData(PreConeccion_Controlador.server);
        }
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

        if(PreConeccion_Controlador.server != null){
            food = new Paquete06Comida(posiciones[0], posiciones[1], indice);
            food.enviarData(PreConeccion_Controlador.server);
        }
        desactivarEfectos();
        //Sonido cuando genera comida especial
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
     * Decide que tipo de comida especial se va a generar
     * 
     * @return Un numero aleatorio que representa un tipo de comida especial
     */
    public int comidaAleatoria(){
        return (int) (Math.random() * ((cantidadComidasEspeciales - 1) + 1));
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
     * @version 1.1.1
     */
    public void personajeSobreComida(ArrayList<Jugador> jugadores){
        for(int i = 0; i < personajes.size(); i++){
            if(personajes.get(i).getCuerpo(0).getPosX() == comidaRegular.getPosX() && personajes.get(i).getCuerpo(0).getPosY() == comidaRegular.getPosY()){
                comidaRegular.hacerEfecto(personajes.get(i));
                comidaEstandarSFX();
                comidaRegular = null;
                if(PreConeccion_Controlador.server != null){
                    food = new Paquete06Comida(0, 0, -2);
                    food.enviarData(PreConeccion_Controlador.server);
                }
                generarComida();
                jugadores.get(i).aumentaPuntaje();
                //Podria ponerse aqui el sonido cuando come
    
            }else if(hayComidaEspecial() && personajes.get(i).getCuerpo(0).getPosX() == comidaEspecial.getPosX() && personajes.get(i).getCuerpo(0).getPosY() == comidaEspecial.getPosY()){
                comidaEspecial.hacerEfecto(personajes.get(i));
                comidaEspecialSFX();
                comidaEspecial = null;
                if(PreConeccion_Controlador.server != null){
                    food = new Paquete06Comida(0, 0, -1);
                    food.enviarData(PreConeccion_Controlador.server);
                }
                tiempoComidaEspecial = 60;
                jugadores.get(i).aumentaPuntaje();
            }
        }
        
    }

    /**
     * Inicializa un tipo de mapa a eleccion del jugador
     * 
     * @param tipo Tipo de mapa que se va a inicializar
     * @version 1.1.4
     */
    public void inicializarTablero(int tipo){
        for(int i = 0; i < alto; i++){
            for(int j = 0; j < ancho; j++){ //Inicializa los valores de cada celda
                if(i == 0 || i == alto - 1 || j == 0 || j == ancho - 1){ //Si esta en el borde, es una pared
                    celdas[i][j] = new Celda("Pared");
                    
                }else{
                    celdas[i][j] = new Celda("Piso");
                    
                }
            }
        }

        switch(tipo){
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

    public void chequeaPersonajes(){
        for(int i = 0; i < personajes.size(); i++){
            personajes.get(i).movimiento(i);
            personajes.get(i).chocaConCuerpo();
            chocaConSerpiente(i);
            chocaConPared(i);
        }
    }

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

                if(PreConeccion_Controlador.server != null){
                    food = new Paquete06Comida(0, 0, -1);
                    food.enviarData(PreConeccion_Controlador.server);
                }
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
        }
    }

    public void chocaConSerpiente(int puesto){
        for(int i = 0; i < personajes.size(); i++){
            if(i != puesto){
                for(int j = 0; j < personajes.get(i).getLongitud(); j++){
                    if(personajes.get(puesto).getCuerpo(0).getPosX() == personajes.get(i).getCuerpo(j).getPosX() && personajes.get(puesto).getCuerpo(0).getPosY() == personajes.get(i).getCuerpo(j).getPosY()){
                        personajes.get(puesto).setEstado(false);
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

    public void desactivarEfectos(){
        for(int i = 0; i < personajes.size(); i++){
            personajes.get(i).descongelar();
        }
        desactivaRapidez();
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

    public void disminuyeTiempoEspecial(){
        tiempoComidaEspecial--;
    }

    public void iniciarPersonajes(ArrayList<Jugador> jugadores){
        for(int i = 0; i < jugadores.size(); i++){
            personajes.add(jugadores.get(i).getPersonaje());
        }
    }
    
    public void setPausa(boolean pausa){
        this.pausa = pausa;
    }

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
}