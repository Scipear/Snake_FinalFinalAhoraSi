package snake2;
/**
 * Clase que representa un jugador
 * 
 * @version 1.0.4
 */
public class Jugador implements Comparable<Jugador>{
    private Personaje personaje; //Personaje que sera controlado por el jugador
    private String usuario; //Nombre de usuario ingresado al iniciar el juego
    private boolean host; //Si el jugador creo un servidor o no
    private int puntaje; //Puntos obtenidos durante la partida
    private int numero; //Numero para identificar al jugador

    public Jugador(String usuario, int numero){
        this.usuario = usuario;
        this.numero = numero;
        puntaje = 0;
        activarHost();
    }

    public Jugador(String usuario, int numero, int x, int y, String direccion, int skin){
        this.usuario = usuario;
        this.numero = numero;
        puntaje = 0;
        personaje = new Personaje(x, y, direccion, skin, numero);
    }
    
    /**
     * Aumenta el puntaje del jugador
     * 
     * @version 1.1.4
     */
    public void aumentaPuntaje(){
        puntaje = personaje.getLongitud() - 3;
    }
    /**
     * Le asigna un personaje al jugador
     * 
     * @param x Posicion en X del personaje
     * @param y Posicion en Y del personaje
     * @param direccion A donde esta mirando el personaje
     * @param skin Avatar del personaje
     * @version 1.2
     */
    public void asignaPersonaje(int x, int y, String direccion, int skin){
        personaje = new Personaje(x, y, direccion, skin, numero);
    }
    
    public void activarHost(){
        if(numero == 0){
            host = true;
        }
    }

    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }

    public int getPuntaje(){
        return puntaje;
    }

    public String getUsuario(){
        return usuario;
    }

    public Personaje getPersonaje(){
        return personaje;
    }

    public int getNumero(){
        return numero;
    }

    @Override
    public int compareTo(Jugador o) {
        return o.getPuntaje() - puntaje;
    }
}
