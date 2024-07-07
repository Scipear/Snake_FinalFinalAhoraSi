package snake2;
/**
 * Clase parte del back
 * 
 * @version 1.0.4
 */
public class Jugador{
    private Personaje personaje;
    private int puntaje;
    private String usuario;
    private int numero;
    private boolean estaListo;
    private boolean host;

    public Jugador(String usuario, int numero){
        this.usuario = usuario;
        this.numero = numero;
        puntaje = 0;
        estaListo = false;
        activarHost();
    }

    public Jugador(String usuario, int numero, int x, int y, String direccion, int skin){
        this.usuario = usuario;
        this.numero = numero;
        puntaje = 0;
        personaje = new Personaje(x, y, direccion, skin);
    }
    
    /**
     * Aumenta el puntaje del jugador
     * 
     * @version 1.1.4
     */
    public void aumentaPuntaje(){
        puntaje++;
    }

    public void asignaPersonaje(int x, int y, String direccion, int skin){
        personaje = new Personaje(x, y, direccion, skin);
    }
    
    public void alistaJugador(boolean estaListo){
        this.estaListo = estaListo;
    }
    
    public void activarHost(){
        if(numero == 0){
            host = true;
        }
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

    public boolean getEstaListo(){
        return estaListo;
    }

}
