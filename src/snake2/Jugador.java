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
    //Este es un modificador

    public Jugador(String usuario, int x, int y, String direccion, int skin){
        this.usuario = usuario;
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
}
