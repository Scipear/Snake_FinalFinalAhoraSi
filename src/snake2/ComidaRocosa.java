package snake2;

/**
 * Clase parte del back. Hereda de la clase Comida
 * 
 * @version 1.1.4
 */
public class ComidaRocosa extends Comida{
    public ComidaRocosa(int posX, int posY){
        super(posX, posY);
    }

    /**
     * Aplica un efecto en el juego o al personaje cuando se es comido
     * 
     * @param personaje Personaje al que se le va a aplicar el efecto de la comida cuando este la coma
     * @version 1.1.4
     */
    public void hacerEfecto(Personaje personaje){
        personaje.disminuyeCuerpo();
    }
}