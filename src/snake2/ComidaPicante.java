package snake2;

import ost.ReproductorSonidos;

/**
 * Clase parte del back. Hereda de la clase Comida
 *
 * @version 1.1.4
 */
public class ComidaPicante extends Comida {

    private final ReproductorSonidos ostSFX;
    private final String sfxOstRuta;

    public ComidaPicante(int posX, int posY) {
        super(posX, posY);
        
        this.sfxOstRuta = "\\src\\ost\\ComidaPicante_SFX.mp3";
        ostSFX = new ReproductorSonidos();
        ostSFX.reproducirSFX(sfxOstRuta);
    }

    /**
     * Aplica un efecto en el juego o al personaje cuando se es comido
     *
     * @param personaje Personaje al que se le va a aplicar el efecto de la
     * comida cuando este la coma
     * @version 1.1.4
     */
    public void hacerEfecto(Personaje personaje) {
        Game.setDelay(100);
        Tablero.setRapidez(true);
        super.hacerEfecto(personaje);
    }
}
