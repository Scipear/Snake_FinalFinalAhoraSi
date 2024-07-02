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

    public Jugador() {
    }

    public Jugador(String usuario) {
        this.usuario = usuario;
    }
    
    

    public Jugador(String usuario, int x, int y, String direccion, int skin){
        this.usuario = usuario;
        puntaje = 0;
        personaje = new Personaje(x, y, direccion, skin);
    }

    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }

    public int getPuntaje(){
        return puntaje;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    

    public String getUsuario(){
        return usuario;
    }

    public Personaje getPersonaje(){
        return personaje;
    }
}
