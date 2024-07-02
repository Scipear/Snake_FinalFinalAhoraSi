package snake2;

/**
 * Clase parte del back
 * 
 * @version 1.1
 */
public class Comida {
    protected int posX;
    protected int posY;
    protected int tiempoVisible;

    public Comida(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        tiempoVisible = 30;
    }

    /**
     * Aplica un efecto en el juego o al personaje cuando se es comido
     * 
     * @param personaje Personaje al que se le va a aplicar el efecto de la comida cuando este la coma
     * @version 1.1
     */
    public void hacerEfecto(Personaje personaje){
        personaje.aumentaLongitud();;
        personaje.creceCuerpo();
    }

    /**
     * Actualiza el tiempo en el que una comida puede ser visible en el tablero
     * 
     * @version 1.1.4
     */
    public void actualizaTiempo(){
        tiempoVisible--;
    }

    public int getTiempoVisible(){
        return tiempoVisible;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }
}
